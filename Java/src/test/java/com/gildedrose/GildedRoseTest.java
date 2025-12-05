package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.Assertions.assertThat;

class GildedRoseTest {
    //region Normal Item Tests

    @Test
    void normalItemQualityDecreasesByOneBeforeSellDate() {
        // Given
        Item[] items = new Item[]{
            new Item("Normal Item", 5, 10),
            new Item("Normal Item", 1, 10)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasSellIn(4).hasQuality(9);
        assertThat(items[1]).hasSellIn(0).hasQuality(9);
    }

    @Test
    void normalItemQualityDecreasesByTwoAfterSellDate() {
        // Given
        Item[] items = new Item[]{
            new Item("Normal Item", 0, 10),
            new Item("Normal Item", -1, 10)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasSellIn(-1).hasQuality(8);
        assertThat(items[1]).hasSellIn(-2).hasQuality(8);
    }

    @Test
    void normalItemQualityNeverBecomesNegative() {
        // Given
        Item[] items = new Item[]{
            new Item("Normal Item", 1, 0),
            new Item("Normal Item", 0, 0),
            new Item("Normal Item", -1, 0)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasSellIn(0).hasQuality(0);
        assertThat(items[1]).hasSellIn(-1).hasQuality(0);
        assertThat(items[2]).hasSellIn(-2).hasQuality(0);
    }
    //endregion

    //region Aged Brie Tests

    @Test
    void agedBrieIncreasesInQualityBeforeSellDate() {
        // Given
        Item[] items = new Item[]{new Item("Aged Brie", 5, 10)};
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasSellIn(4).hasQuality(11);
    }

    @Test
    void agedBrieIncreasesInQualityTwiceAsFastAfterSellDate() {
        // Given
        Item[] items = new Item[]{new Item("Aged Brie", 0, 10)};
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasSellIn(-1).hasQuality(12);
    }

    @Test
    void agedBrieQualityNeverExceedsFifty() {
        // Given
        Item[] items = new Item[]{
            new Item("Aged Brie", 5, 50),
            new Item("Aged Brie", 0, 49)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasSellIn(4).hasQuality(50);
        assertThat(items[1]).hasSellIn(-1).hasQuality(50);
    }
    //endregion

    //region Sulfuras Tests

    @Test
    void sulfurasNeverDecreasesInQuality() {
        // Given
        Item[] items = new Item[]{
            new Item("Sulfuras, Hand of Ragnaros", 1, 80),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasQuality(80);
        assertThat(items[1]).hasQuality(80);
        assertThat(items[2]).hasQuality(80);
    }

    @Test
    void sulfurasNeverHasToBeSold() {
        // Given
        Item[] items = new Item[]{
            new Item("Sulfuras, Hand of Ragnaros", 1, 80),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasSellIn(1);
        assertThat(items[1]).hasSellIn(0);
        assertThat(items[2]).hasSellIn(-1);
    }
    //endregion

    //region Backstage Passes Tests

    @Test
    void backstagePassesIncreaseInQualityByOneWhenMoreThanTenDays() {
        // Given
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20),
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasSellIn(14).hasQuality(21);
        assertThat(items[1]).hasSellIn(10).hasQuality(21);
    }

    @Test
    void backstagePassesIncreaseInQualityByTwoWhenBetweenTenAndSixDays() {
        // Given
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 9, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 8, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 7, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20),
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasSellIn(9).hasQuality(22);
        assertThat(items[1]).hasSellIn(8).hasQuality(22);
        assertThat(items[2]).hasSellIn(7).hasQuality(22);
        assertThat(items[3]).hasSellIn(6).hasQuality(22);
        assertThat(items[4]).hasSellIn(5).hasQuality(22);
    }

    @Test
    void backstagePassesIncreaseInQualityByThreeWhenFiveDaysOrLess() {
        // Given
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 4, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 3, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 2, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20),
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasSellIn(4).hasQuality(23);
        assertThat(items[1]).hasSellIn(3).hasQuality(23);
        assertThat(items[2]).hasSellIn(2).hasQuality(23);
        assertThat(items[3]).hasSellIn(1).hasQuality(23);
        assertThat(items[4]).hasSellIn(0).hasQuality(23);
    }

    @Test
    void backstagePassesQualityNeverExceedsFifty() {
        // Given
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 50),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasQuality(50);
        assertThat(items[1]).hasQuality(50);
        assertThat(items[2]).hasQuality(50);
        assertThat(items[3]).hasQuality(50);
        assertThat(items[4]).hasQuality(50);
    }

    @Test
    void backstagePassesQualityDropsToZeroAfterConcert() {
        // Given
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasSellIn(-1).hasQuality(0);
    }
    //endregion

    //region Conjured Items Tests
    // TODO[Bart]: Can Conjured items act like a modifier on any item (normal, brie, backstage pass, ...)?

    @Test
    void conjuredItemsDegradeInQualityTwiceAsFastBeforeSellDate() {
        // Given
        Item[] items = new Item[]{
            new Item("Conjured Mana Cake", 5, 10),
            new Item("Conjured Mana Cake", 1, 10),
            new Item("Conjured Mana Cake", 0, 10),
            new Item("Conjured Mana Cake", -1, 10)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasQuality(8);
        assertThat(items[1]).hasQuality(8);
        assertThat(items[2]).hasQuality(6);
        assertThat(items[3]).hasQuality(6);
    }
    //endregion

    //region General quality Tests

    @Test
    void qualityNeverExceedsFifty() {
        // Given
        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 50),
            new Item("Aged Brie", 2, 50),
            new Item("Elixir of the Mongoose", 5, 50),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50),
            new Item("Conjured Mana Cake", 3, 50)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasQuality(49);
        assertThat(items[1]).hasQuality(50);
        assertThat(items[2]).hasQuality(49);
        assertThat(items[3]).hasQuality(80); // Quality of Sulfuras never changes
        assertThat(items[4]).hasQuality(50);
        assertThat(items[5]).hasQuality(48);
    }

    @Test
    void qualityDegradesTwiceAsFastAfterSellDate() {
        // Given
        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 0, 10),
            new Item("Aged Brie", 0, 10),
            new Item("Elixir of the Mongoose", 0, 10),
            new Item("Sulfuras, Hand of Ragnaros", 0, 10),
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10),
            new Item("Conjured Mana Cake", 0, 10)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasQuality(8);
        assertThat(items[1]).hasQuality(12);
        assertThat(items[2]).hasQuality(8);
        assertThat(items[3]).hasQuality(10); // Quality of Sulfuras never changes
        assertThat(items[4]).hasQuality(0);
        assertThat(items[5]).hasQuality(6);
    }

    @Test
    void qualityNeverGetsNegative() {
        // Given
        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 0),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 0),
            new Item("Sulfuras, Hand of Ragnaros", 0, -1),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 0),
            new Item("Conjured Mana Cake", 3, 0)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasQuality(0);
        assertThat(items[1]).hasQuality(1);
        assertThat(items[2]).hasQuality(0);
        assertThat(items[3]).hasQuality(-1); // Quality of Sulfuras never changes
        assertThat(items[4]).hasQuality(1);
        assertThat(items[5]).hasQuality(0);
    }
    //endregion

    //region SellIn Test

    @Test
    void sellInDescreasesByOne() {
        // Given
        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 10),
            new Item("Aged Brie", 10, 10),
            new Item("Elixir of the Mongoose", 10, 10),
            new Item("Sulfuras, Hand of Ragnaros", 10, 10),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10),
            new Item("Conjured Mana Cake", 10, 10)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertThat(items[0]).hasSellIn(9);
        assertThat(items[1]).hasSellIn(9);
        assertThat(items[2]).hasSellIn(9);
        assertThat(items[3]).hasSellIn(10); // Sulfuras never has to be sold
        assertThat(items[4]).hasSellIn(9);
        assertThat(items[5]).hasSellIn(9);
    }

    //endregion
}
