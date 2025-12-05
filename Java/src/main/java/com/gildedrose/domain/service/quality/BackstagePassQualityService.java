package com.gildedrose.domain.service.quality;

import com.gildedrose.Item;

public class BackstagePassQualityService extends BaseQualityService {
    private static final int BACKSTAGE_PASS_DAILY_QUALITY_CHANGE = 1;
    private static final int SELL_IN_LIMIT_HIGH = 5;
    private static final int SELL_IN_LIMIT_MID = 10;
    private static final int DAILY_QUALITY_CHANGE_MODIFIER_HIGH = 3;
    private static final int DAILY_QUALITY_CHANGE_MODIFIER_MID = 2;

    @Override
    protected int calculateNewQuality(Item item) {
        if (isAfterSellDate(item)) {
            return 0;
        }

        int qualityChange = getQualityChange(item);
        return item.quality + qualityChange;
    }

    private int getQualityChange(Item item) {
        int qualityChange;
        if (item.sellIn <= SELL_IN_LIMIT_HIGH) {
            qualityChange = BACKSTAGE_PASS_DAILY_QUALITY_CHANGE * DAILY_QUALITY_CHANGE_MODIFIER_HIGH;
        } else if (item.sellIn <= SELL_IN_LIMIT_MID) {
            qualityChange = BACKSTAGE_PASS_DAILY_QUALITY_CHANGE * DAILY_QUALITY_CHANGE_MODIFIER_MID;
        } else {
            qualityChange = BACKSTAGE_PASS_DAILY_QUALITY_CHANGE;
        }
        return qualityChange;
    }
}
