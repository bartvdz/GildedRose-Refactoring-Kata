package com.gildedrose;

class GildedRose {
    private static final String LABEL_BACKSTAGE_PASSES = "BACKSTAGE PASSES";
    private static final String LABEL_AGED_BRIE = "AGED BRIE";
    private static final String LABEL_SULFURAS = "SULFURAS";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Aged Brie")
                    && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > 0) {
                            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
    /**
     * Convenience record, that can determine type once and group it with original item.
     *
     * @param item Item that is to be processed
     * @param type Item type matching the provided item
     */
    private record TypedItem(Item item, Type type) {
        /**
         * Convenience record that can determine type of provide item itself.
         *
         * @param item Item that is to be processed
         */
        private TypedItem(Item item) {
            this(item, getItemType(item));
        }

        private static Type getItemType(Item item) {
            // Raising string to uppercase for ease of case-insensitive string comparison
            String upperCaseName = item.name.toUpperCase();

            if (upperCaseName.contains(LABEL_BACKSTAGE_PASSES)) {
                return Type.BACKSTAGE_PASS;
            }

            if (upperCaseName.contains(LABEL_AGED_BRIE)) {
                return Type.AGED_BRIE;
            }

            if (upperCaseName.contains(LABEL_SULFURAS)) {
                return Type.SULFURAS;
            }

            return Type.NORMAL;
        }
    }

    /**
     * All the different known item types
     */
    private enum Type {
        NORMAL,
        AGED_BRIE,
        BACKSTAGE_PASS,
        SULFURAS
    }
}
