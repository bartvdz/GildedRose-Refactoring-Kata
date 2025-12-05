package com.gildedrose.domain.service.quality;

import com.gildedrose.domain.model.item.*;

public class QualityServiceFactory {
    public QualityService create(ItemInfo itemInfo) {
        QualityService qualityService = switch (itemInfo.itemCategory()) {
            case AgedBrieItem agedBrieItem -> new AgedBrieQualityService();
            case BackstagePassItem backstagePassItem -> new BackstagePassQualityService();
            case StandardItem standardItem -> new StandardQualityService();
            case SulfurasItem sulfurasItem -> new SulfurasQualityService();
        };

        if (itemInfo.isConjured()) {
            qualityService = new ConjuredQualityServiceDecorator(qualityService);
        }

        return qualityService;
    }
}
