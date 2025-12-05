package com.gildedrose.domain.service.quality;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BackstagePassQualityServiceTest extends QualityServiceTest {

    @BeforeEach
    void setUp() {
        service = new BackstagePassQualityService();
    }

    @Override
    Item createItem(int quality, int sellIn) {
        return new Item("Backstage pass", sellIn, quality);
    }

    @Test
    void backstagePassQualityIncreasesByOneWhenMoreThanTenDays() {
        assertUpdateQualityItem(20, 15, 21);
        assertUpdateQualityItem(20, 11, 21);
        assertUpdateQualityItem(0, 11, 1);
    }

    @Test
    void backstagePassQualityIncreasesByTwoBetweenSixAndTenDays() {
        assertUpdateQualityItem(20, 10, 22);
        assertUpdateQualityItem(20, 8, 22);
        assertUpdateQualityItem(20, 6, 22);
        assertUpdateQualityItem(0, 6, 2);
    }

    @Test
    void backstagePassQualityIncreasesByThreeAtFiveOrLessDays() {
        assertUpdateQualityItem(20, 5, 23);
        assertUpdateQualityItem(20, 3, 23);
        assertUpdateQualityItem(20, 1, 23);
        assertUpdateQualityItem(0, 1, 3);
    }
    @Test
    void backstagePassQualityDropsToZeroAtZeroDays() {
        assertUpdateQualityItem(20, 0, 0);
        assertUpdateQualityItem(0, 0, 0);
    }

    @Test
    void backstagePassQualityNeverExceedsMaximum() {
        assertUpdateQualityItem(50, 15, 50);
        assertUpdateQualityItem(50, 8, 50);
        assertUpdateQualityItem(49, 8, 50);
        assertUpdateQualityItem(50, 3, 50);
        assertUpdateQualityItem(48, 3, 50);
    }
}
