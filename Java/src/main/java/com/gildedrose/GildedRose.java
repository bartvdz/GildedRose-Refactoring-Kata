package com.gildedrose;

class GildedRose {
    private static final String LABEL_BACKSTAGE_PASSES = "BACKSTAGE PASSES";
    private static final String LABEL_AGED_BRIE = "AGED BRIE";
    private static final String LABEL_SULFURAS = "SULFURAS";

    private static final int NORMAL_DEGRADATION = -1;
    private static final int AGED_BRIE_DEGRADATION = 1;
    private static final int BACKSTAGE_PASS_BASE_DEGRADATION = 1;
    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            TypedItem typedItem = new TypedItem(item);

            item.quality = getNextQuality(typedItem);
            // Only update SellIn after quality has been processed, as it influences the quality determination
            item.sellIn = getNextSellIn(typedItem);
        }
    }

    private int getNextQuality(TypedItem typedItem) {
        Item item = typedItem.item();

        int passedSellInMultiplier = item.sellIn <= 0 ? 2 : 1;

        int newQuality = switch (typedItem.type()) {
            case NORMAL -> item.quality + NORMAL_DEGRADATION * passedSellInMultiplier;
            case AGED_BRIE -> item.quality + AGED_BRIE_DEGRADATION * passedSellInMultiplier;
            case BACKSTAGE_PASS -> getNextBackStageQuality(item);
            case SULFURAS -> item.quality;
        };

        if (typedItem.type() == Type.SULFURAS) {
            return newQuality;
        }

        return Integer.min(Integer.max(MIN_QUALITY, newQuality), MAX_QUALITY);
    }

    private int getNextBackStageQuality(Item item) {
        if (item.sellIn <= 0) {
            return 0;
        } else if (item.sellIn <= 5) {
            return item.quality + BACKSTAGE_PASS_BASE_DEGRADATION * 3;
        } else if (item.sellIn <= 10) {
            return item.quality + BACKSTAGE_PASS_BASE_DEGRADATION * 2;
        } else {
            return item.quality + BACKSTAGE_PASS_BASE_DEGRADATION;
        }

        // TODO: Use primitive type in pattern switching when it gets out of preview.
        //       This makes it even easier to follow and compiler checks if all cases are covered.
        //
        // return switch (item.sellIn) {
        //     case int sellIn when sellIn <= 0 -> 0;
        //     case int sellIn when sellIn <= 5 -> item.quality + NORMAL_DEGRADATION * 3;
        //     case int sellIn when sellIn <= 10 -> item.quality + NORMAL_DEGRADATION * 2;
        //     case int _ -> item.quality + NORMAL_DEGRADATION;
        // };
    }

    private int getNextSellIn(TypedItem typedItem) {
        if (typedItem.type() == Type.SULFURAS) {
            return typedItem.item().sellIn;
        }

        return typedItem.item().sellIn - 1;
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
