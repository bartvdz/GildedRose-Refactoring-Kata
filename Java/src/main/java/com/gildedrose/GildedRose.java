package com.gildedrose;

import com.gildedrose.domain.model.item.ItemCategory;
import com.gildedrose.domain.service.ItemCategoryResolverService;
import com.gildedrose.domain.service.quality.QualityService;
import com.gildedrose.domain.service.quality.QualityServiceFactory;
import com.gildedrose.domain.service.sellin.SellInService;
import com.gildedrose.domain.service.sellin.SellInService;
import com.gildedrose.domain.service.sellin.SellInServiceFactory;

class GildedRose {
    private final Item[] items;
    private final ItemCategoryResolverService itemCategoryResolverService;
    private final QualityServiceFactory qualityServiceFactory;
    private final SellInServiceFactory sellInServiceFactory;

    public GildedRose(Item[] items) {
        this.items = items;

        itemCategoryResolverService = new ItemCategoryResolverService();
        qualityServiceFactory = new QualityServiceFactory();
        sellInServiceFactory = new SellInServiceFactory();
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemCategory category = itemCategoryResolverService.resolve(item);
            updateItemQuality(item, category);
            updateItemSellIn(item, category);
        }
    }

    private void updateItemQuality(Item item, ItemCategory category) {
        QualityService qualityService = qualityServiceFactory.create(category);
        item.quality = qualityService.updateQuality(item).quality;
    }

    private void updateItemSellIn(Item item, ItemCategory category) {
        SellInService sellInService = sellInServiceFactory.create(category);
        item.sellIn = sellInService.updateSellIn(item).sellIn;
    }
}
