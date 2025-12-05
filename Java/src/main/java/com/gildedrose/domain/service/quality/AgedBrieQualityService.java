package com.gildedrose.domain.service.quality;

import com.gildedrose.Item;

public class AgedBrieQualityService extends BaseQualityService {
    private static final int AGED_BRIE_DAILY_QUALITY_CHANGE = 1;
    private static final int AGED_BRIE_DAILY_QUALITY_CHANGE_AFTER_SELL_DATE = 2;

    @Override
    protected int calculateNewQuality(Item item) {
        int qualityChange = getQualityChange(item);
        return item.quality + qualityChange;
    }

    private int getQualityChange(Item item) {
        int qualityChange;
        if (isAfterSellDate(item)) {
            qualityChange = AGED_BRIE_DAILY_QUALITY_CHANGE_AFTER_SELL_DATE;
        } else {
            qualityChange = AGED_BRIE_DAILY_QUALITY_CHANGE;
        }
        return qualityChange;
    }
}
