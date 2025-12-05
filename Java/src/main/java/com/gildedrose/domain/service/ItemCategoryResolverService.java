package com.gildedrose.domain.service;

import com.gildedrose.Item;
import com.gildedrose.domain.model.item.*;

public class ItemCategoryResolverService {
    private static final String LABEL_BACKSTAGE_PASSES = "BACKSTAGE PASSES";
    private static final String LABEL_AGED_BRIE = "AGED BRIE";
    private static final String LABEL_SULFURAS = "SULFURAS";

    public ItemCategory resolve(Item item) {
        String upperCaseName = item.name.toUpperCase();

        if (upperCaseName.contains(LABEL_BACKSTAGE_PASSES)) {
            return new BackstagePassItem();
        }

        if (upperCaseName.contains(LABEL_AGED_BRIE)) {
            return new AgedBrieItem();
        }

        if (upperCaseName.contains(LABEL_SULFURAS)) {
            return new SulfurasItem();
        }

        return new StandardItem();
    }
}
