package com.gildedrose.domain.service.quality;

import com.gildedrose.Item;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public abstract class QualityServiceTest {

    QualityService service;

    abstract Item createItem(int quality, int sellIn);

    void assertUpdateQualityItem(int quality, int sellIn, int expectedQuality) {
        // Given
        Item item = createItem(quality, sellIn);

        // When
        Item updatedItem = service.updateQuality(item);

        // Then
        assertThat(updatedItem.quality).isEqualTo(expectedQuality);
    }
}
