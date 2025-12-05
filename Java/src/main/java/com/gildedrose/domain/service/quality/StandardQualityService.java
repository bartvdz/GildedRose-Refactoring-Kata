package com.gildedrose.domain.service.quality;

import com.gildedrose.Item;

public class StandardQualityService extends BaseQualityService {

    private static final int STANDARD_DAILY_QUALITY_CHANGE = -1;
    private static final int STANDARD_DAILY_QUALITY_CHANGE_AFTER_SELL_DATE = -2;

    @Override
    protected int calculateNewQuality(Item item) {
        int qualityChange = getQualityChange(item);
        return item.quality + qualityChange;
    }

    private int getQualityChange(Item item) {
        int qualityChange;
        if (isAfterSellDate(item)) {
            qualityChange = STANDARD_DAILY_QUALITY_CHANGE_AFTER_SELL_DATE;
        } else {
            qualityChange = STANDARD_DAILY_QUALITY_CHANGE;
        }
        return qualityChange;
    }
}
