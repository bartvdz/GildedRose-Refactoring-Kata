package com.gildedrose.domain.service.sellin;

import com.gildedrose.Item;

public class StandardSellInService implements SellInService {

    private static final int ONE_DAY = 1;

    @Override
    public Item updateSellIn(Item item) {
        int newSellIn = item.sellIn - ONE_DAY;
        return new Item(item.name, newSellIn, item.quality);
    }
}
