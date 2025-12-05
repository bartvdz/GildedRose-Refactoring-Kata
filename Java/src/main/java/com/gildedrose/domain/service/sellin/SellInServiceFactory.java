package com.gildedrose.domain.service.sellin;

import com.gildedrose.domain.model.item.*;

public class SellInServiceFactory {
    public SellInService create(ItemInfo itemInfo) {
        return switch (itemInfo.itemCategory()) {
            case AgedBrieItem agedBrieItem -> new StandardSellInService();
            case BackstagePassItem backstagePassItem -> new StandardSellInService();
            case StandardItem standardItem -> new StandardSellInService();
            case SulfurasItem sulfurasItem -> new SulfurasSellInService();
        };
    }
}
