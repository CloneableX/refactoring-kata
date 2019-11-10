package com.gildedrose.item;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArgedBrieTest {
    @Test
    public void should_increment_quality_when_give_arged_brie_after_sell_in() {
        ArgedBrie argedBrie = new ArgedBrie(-1, 0);
        argedBrie.updateQualityAfterSellIn();
        assertThat(argedBrie.quality, is(1));
    }
}
