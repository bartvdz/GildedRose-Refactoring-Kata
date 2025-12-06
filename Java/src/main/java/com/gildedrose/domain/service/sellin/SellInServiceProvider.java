package com.gildedrose.domain.service.sellin;

import com.gildedrose.domain.model.item.*;

import java.util.HashMap;
import java.util.Map;

public class SellInServiceProvider {
    private final Map<ItemInfo, SellInService> itemInfoSellInServiceMap;

    public SellInServiceProvider() {
        itemInfoSellInServiceMap = new HashMap<>();
    }

    public SellInService get(ItemInfo itemInfo) {
        return itemInfoSellInServiceMap.computeIfAbsent(itemInfo, this::getSellInService);
    }

    private SellInService getSellInService(ItemInfo itemInfo) {
        return switch (itemInfo.itemCategory()) {
            case AgedBrieItem agedBrieItem -> new StandardSellInService();
            case BackstagePassItem backstagePassItem -> new StandardSellInService();
            case StandardItem standardItem -> new StandardSellInService();
            case SulfurasItem sulfurasItem -> new SulfurasSellInService();
        };
    }
}
