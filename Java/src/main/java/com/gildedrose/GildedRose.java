package com.gildedrose;

import com.gildedrose.domain.model.item.ItemInfo;
import com.gildedrose.domain.service.ItemInfoService;
import com.gildedrose.domain.service.quality.QualityService;
import com.gildedrose.domain.service.quality.QualityServiceProvider;
import com.gildedrose.domain.service.sellin.SellInService;
import com.gildedrose.domain.service.sellin.SellInServiceProvider;

class GildedRose {
    private final Item[] items;
    private final ItemInfoService itemInfoService;
    private final QualityServiceProvider qualityServiceProvider;
    private final SellInServiceProvider sellInServiceProvider;

    public GildedRose(Item[] items) {
        this.items = items;

        itemInfoService = new ItemInfoService();
        qualityServiceProvider = new QualityServiceProvider();
        sellInServiceProvider = new SellInServiceProvider();
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemInfo itemInfo = itemInfoService.parseItem(item);
            updateItemQuality(item, itemInfo);
            updateItemSellIn(item, itemInfo);
        }
    }

    private void updateItemQuality(Item item, ItemInfo itemInfo) {
        QualityService qualityService = qualityServiceProvider.get(itemInfo);
        item.quality = qualityService.updateQuality(item).quality;
    }

    private void updateItemSellIn(Item item, ItemInfo itemInfo) {
        SellInService sellInService = sellInServiceProvider.get(itemInfo);
        item.sellIn = sellInService.updateSellIn(item).sellIn;
    }
}
