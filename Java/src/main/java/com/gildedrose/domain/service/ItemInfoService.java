package com.gildedrose.domain.service;

import com.gildedrose.Item;
import com.gildedrose.domain.model.item.*;

public class ItemInfoService {
    private static final String LABEL_BACKSTAGE_PASSES = "BACKSTAGE PASSES";
    private static final String LABEL_AGED_BRIE = "AGED BRIE";
    private static final String LABEL_SULFURAS = "SULFURAS";
    private static final String LABEL_CONJURED = "CONJURED";

    public ItemInfo parseItem(Item item) {
        String upperCaseName = item.name.toUpperCase();
        return new ItemInfo(resolve(upperCaseName), isConjured(upperCaseName));
    }

    private ItemCategory resolve(String upperCaseName) {
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

    private boolean isConjured(String upperCaseName) {
        return upperCaseName.contains(LABEL_CONJURED);
    }
}
