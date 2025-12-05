package com.gildedrose.domain.service.quality;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SulfurasQualityServiceTest  extends QualityServiceTest {

    @BeforeEach
    void setUp() {
        service = new SulfurasQualityService();
    }

    @Override
    Item createItem(int quality, int sellIn) {
        return new Item("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }

    @Test
    void sulfurasQualityNeverChanges() {
        assertUpdateQualityItem(0, 10, 0);
        assertUpdateQualityItem(1, 10, 1);
        assertUpdateQualityItem(25, 10, 25);
        assertUpdateQualityItem(50, 10, 50);
        assertUpdateQualityItem(-50, 10, -50);
    }

    @Test
    void sulfurasQualityIsNotLimitedByBaseServiceMaximumConstraint() {
        assertUpdateQualityItem(80, 10, 80);
    }

    @Test
    void sulfurasQualityNeverChangesRegardlessOfSellIn() {
        assertUpdateQualityItem(30, 10, 30);
        assertUpdateQualityItem(30, 0, 30);
        assertUpdateQualityItem(30, -5, 30);
    }
}
