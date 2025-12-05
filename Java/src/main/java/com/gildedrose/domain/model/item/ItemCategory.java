package com.gildedrose.domain.model.item;

public sealed interface ItemCategory permits StandardItem, AgedBrieItem, SulfurasItem, BackstagePassItem {
}
