package com.gildedrose.domain.service.quality;

import com.gildedrose.Item;

public class ConjuredQualityServiceDecorator implements QualityService {
    private final QualityService baseService;

    public ConjuredQualityServiceDecorator(QualityService baseService) {
        this.baseService = baseService;
    }

    @Override
    public Item updateQuality(Item item) {
        Item updatedItem = baseService.updateQuality(item);
        return baseService.updateQuality(updatedItem);
    }
}
