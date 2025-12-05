package com.gildedrose.domain.service.quality;

import com.gildedrose.Item;

public class SulfurasQualityService extends BaseQualityService {
    private static final int SULFURAS_MAX_QUALITY = Integer.MAX_VALUE;
    private static final int SULFURAS_MIN_QUALITY = Integer.MIN_VALUE;

    @Override
    protected int calculateNewQuality(Item item) {
        return item.quality;
    }

    @Override
    protected int getMaxQuality() {
        return SULFURAS_MAX_QUALITY;
    }

    @Override
    protected int getMinQuality() {
        return SULFURAS_MIN_QUALITY;
    }
}
