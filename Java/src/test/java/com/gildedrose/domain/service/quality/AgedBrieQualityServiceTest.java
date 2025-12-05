package com.gildedrose.domain.service.quality;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AgedBrieQualityServiceTest extends QualityServiceTest {

    @BeforeEach
    void setUp() {
        service = new AgedBrieQualityService();
    }

    @Override
    Item createItem(int quality, int sellIn) {
        return new Item("Aged Brie", sellIn, quality);
    }

    @Test
    void agedBrieQualityIncreasesByOne() {
        assertUpdateQualityItem(20, 10, 21);
        assertUpdateQualityItem(0, 10, 1);
        assertUpdateQualityItem(1, 10, 2);
        assertUpdateQualityItem(49, 10, 50);
    }

    @Test
    void agedBrieQualityIncreasesByTwoPassSellDate() {
        assertUpdateQualityItem(20, 0, 22);
        assertUpdateQualityItem(0, -1, 2);
        assertUpdateQualityItem(1, -1, 3);
        assertUpdateQualityItem(48, 0, 50);
    }

    @Test
    void agedBrieQualityNeverExceedsMaximumFromFifty() {
        assertUpdateQualityItem(50, 10, 50);
        assertUpdateQualityItem(50, 1, 50);
        assertUpdateQualityItem(50, 0, 50);
        assertUpdateQualityItem(50, -1, 50);
    }
}
