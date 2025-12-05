package com.gildedrose.domain.service.quality;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandardQualityServiceTest extends QualityServiceTest {

    @BeforeEach
    void setUp() {
        service = new StandardQualityService();
    }

    @Override
    Item createItem(int quality, int sellIn) {
        return new Item("Standard Item", sellIn, quality);
    }

    @Test
    void standardItemQualityDecreasesByOne() {
        assertUpdateQualityItem(20, 10, 19);
        assertUpdateQualityItem(50, 10, 49);
        assertUpdateQualityItem(49, 10, 48);
        assertUpdateQualityItem(1, 10, 0);
    }

    @Test
    void standardItemQualityDecreasesByTwoAfterSellDate() {
        assertUpdateQualityItem(20, 0, 18);
        assertUpdateQualityItem(50, -1, 48);
        assertUpdateQualityItem(49, -1, 47);
        assertUpdateQualityItem(2, 0, 0);
    }

    @Test
    void standardItemQualityNeverGoesNegativeFromZero() {
        assertUpdateQualityItem(0, 10, 0);
    }
}
