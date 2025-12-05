package com.gildedrose;

import com.gildedrose.domain.model.item.ItemInfo;
import com.gildedrose.domain.service.ItemInfoService;
import com.gildedrose.domain.service.quality.QualityService;
import com.gildedrose.domain.service.quality.QualityServiceFactory;
import com.gildedrose.domain.service.sellin.SellInService;
import com.gildedrose.domain.service.sellin.SellInServiceFactory;

class GildedRose {
    private final Item[] items;
    private final ItemInfoService itemInfoService;
    private final QualityServiceFactory qualityServiceFactory;
    private final SellInServiceFactory sellInServiceFactory;

    public GildedRose(Item[] items) {
        this.items = items;

        itemInfoService = new ItemInfoService();
        qualityServiceFactory = new QualityServiceFactory();
        sellInServiceFactory = new SellInServiceFactory();
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemInfo itemInfo = itemInfoService.parseItem(item);
            updateItemQuality(item, itemInfo);
            updateItemSellIn(item, itemInfo);
        }
    }

    private void updateItemQuality(Item item, ItemInfo itemInfo) {
        QualityService qualityService = qualityServiceFactory.create(itemInfo);
        item.quality = qualityService.updateQuality(item).quality;
    }

    private void updateItemSellIn(Item item, ItemInfo itemInfo) {
        SellInService sellInService = sellInServiceFactory.create(itemInfo);
        item.sellIn = sellInService.updateSellIn(item).sellIn;
    }
}
