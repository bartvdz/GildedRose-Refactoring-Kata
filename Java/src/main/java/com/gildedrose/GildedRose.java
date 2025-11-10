package com.gildedrose;

class GildedRose {
    private static final String LABEL_BACKSTAGE_PASSES = "BACKSTAGE PASSES";
    private static final String LABEL_AGED_BRIE = "AGED BRIE";
    private static final String LABEL_SULFURAS = "SULFURAS";
    private static final String LABEL_CONJURED = "CONJURED";

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
        int conjuredMultiplier = typedItem.isConjured ? 2 : 1;

        int newQuality = switch (typedItem.type()) {
            case NORMAL -> item.quality + NORMAL_DEGRADATION * passedSellInMultiplier * conjuredMultiplier;
            case AGED_BRIE -> item.quality + AGED_BRIE_DEGRADATION * passedSellInMultiplier * conjuredMultiplier;
            case BACKSTAGE_PASS -> getNextBackStageQuality(item, conjuredMultiplier);
            case SULFURAS -> item.quality;
        };

        // Sulfuras its quality never alters -- returning before applying min/max limiting
        if (typedItem.type() == Type.SULFURAS) {
            return newQuality;
        }

        // Limit the quality to the min and max possible values
        return Integer.min(Integer.max(MIN_QUALITY, newQuality), MAX_QUALITY);
    }

    private int getNextBackStageQuality(Item item, int conjuredMultiplier) {
        int qualityModifier;

        if (item.sellIn <= 0) {
            return 0;
        }

        if (item.sellIn <= 5) {
            qualityModifier = BACKSTAGE_PASS_BASE_DEGRADATION * 3 * conjuredMultiplier;
        } else if (item.sellIn <= 10) {
            qualityModifier = BACKSTAGE_PASS_BASE_DEGRADATION * 2 * conjuredMultiplier;
        } else {
            qualityModifier = BACKSTAGE_PASS_BASE_DEGRADATION * conjuredMultiplier;
        }

        return item.quality + qualityModifier;

        // TODO: Use primitive type in pattern switching when it gets out of preview.
        //       This makes it even easier to follow and compiler checks if all cases are covered.
        //
        // return switch (item.sellIn) {
        //     case int sellIn when sellIn <= 0 -> 0;
        //     case int sellIn when sellIn <= 5 -> item.quality + NORMAL_DEGRADATION * 3 * conjuredMultiplier;
        //     case int sellIn when sellIn <= 10 -> item.quality + NORMAL_DEGRADATION * 2 * conjuredMultiplier;
        //     case int _ -> item.quality + NORMAL_DEGRADATION * conjuredMultiplier;
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
     * @param item       Item that is to be processed
     * @param type       Item type matching the provided item
     * @param isConjured Boolean indicating if the item is conjured or not
     */
    private record TypedItem(Item item, Type type, boolean isConjured) {
        /**
         * Convenience record that can determine type of provide item itself.
         *
         * @param item Item that is to be processed
         */
        private TypedItem(Item item) {
            this(item, getItemType(item), isItemConjured(item));
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

        private static boolean isItemConjured(Item item) {
            // Raising string to uppercase for ease of case-insensitive string comparison
            return item.name.toUpperCase().contains(LABEL_CONJURED);
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
