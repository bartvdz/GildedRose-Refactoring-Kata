package com.gildedrose;

import com.gildedrose.domain.model.item.ItemCategory;
import com.gildedrose.domain.service.ItemCategoryResolverService;
import com.gildedrose.domain.service.quality.QualityService;
import com.gildedrose.domain.service.quality.QualityServiceFactory;

class GildedRose {
    private final Item[] items;
    private final ItemCategoryResolverService itemCategoryResolverService;
    private final QualityServiceFactory qualityServiceFactory;

    public GildedRose(Item[] items) {
        this.items = items;

        itemCategoryResolverService = new ItemCategoryResolverService();
        qualityServiceFactory = new QualityServiceFactory();
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }
        }
    }

    private void updateItemQuality(Item item) {
        ItemCategory category = itemCategoryResolverService.resolve(item);
        QualityService qualityService = qualityServiceFactory.create(category);
        item.quality = qualityService.updateQuality(item).quality;
    }
}
