package com.gildedrose.item;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BackstagePassesTest {
    @Test
    public void should_get_zero_quality_when_give_backstage_passes_after_sell_in() {
        BackstagePasses backstagePasses = new BackstagePasses(-1, 20);
        backstagePasses.updateQualityAfterSellIn();
        assertThat(backstagePasses.quality, is(0));
    }
}
