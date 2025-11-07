package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.Assertions.assertThat;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0]).hasName("foo");
    }

    @Test
    void qualityDegradesTwiceAsFastWhenSellDataHasPassed() {
        // Given
        Item[] items = new Item[]{new Item("foo", 1, 10)};
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();
        app.updateQuality();

        // Then
        assertThat(app.items[0]).hasName("foo")
                                .hasSellIn(-1)
                                .hasQuality(7);
    }

    @Test
    void qualityNeverGetsNegative() {
        // Given
        Item[] items = new Item[]{new Item("foo", 1, 1)};
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();
        app.updateQuality();

        // Then
        assertThat(app.items[0]).hasName("foo")
                                .hasSellIn(-1)
                                .hasQuality(0);

    }
}
