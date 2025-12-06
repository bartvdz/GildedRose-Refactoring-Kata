package com.gildedrose.domain.service.quality;

import com.gildedrose.domain.model.item.*;

import java.util.HashMap;
import java.util.Map;

public class QualityServiceProvider {
    private final Map<ItemInfo, QualityService> itemQualityServiceMap;

    public QualityServiceProvider() {
        itemQualityServiceMap = new HashMap<>();
    }

    public QualityService get(ItemInfo itemInfo) {
        return itemQualityServiceMap.computeIfAbsent(itemInfo, this::getQualityService);
    }

    private QualityService getQualityService(ItemInfo itemInfo) {
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
