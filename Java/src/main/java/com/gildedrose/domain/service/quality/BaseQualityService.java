package com.gildedrose.domain.service.quality;

import com.gildedrose.Item;

public abstract class BaseQualityService implements QualityService {
    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;
    protected static final int SELL_DATE_LIMIT = 0;

    @Override
    public Item updateQuality(Item item) {
        int newQuality = calculateNewQuality(item);
        int limitedQuality = limitQuality(newQuality);
        return new Item(item.name, item.sellIn, limitedQuality);
    }

    protected abstract int calculateNewQuality(Item item);

    protected boolean isAfterSellDate(Item item) {
        return item.sellIn <= SELL_DATE_LIMIT;
    }

    protected int getMaxQuality() {
        return MAX_QUALITY;
    }

    protected int getMinQuality() {
        return MIN_QUALITY;
    }

    private int limitQuality(int newQuality) {
        return Integer.min(Integer.max(getMinQuality(), newQuality), getMaxQuality());
    }
}
