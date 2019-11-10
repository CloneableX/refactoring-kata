package com.gildedrose.item;

import com.gildedrose.Item;

public class BackstagePasses extends Item {
    public BackstagePasses(int sellIn, int quality) {
        super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    @Override
    protected void updateQualityAfterSellIn() {
        quality = 0;
    }

    @Override
    protected void updateQuality() {
        incrementQuality();

        if (sellIn >= 11) {
            return;
        }
        incrementQuality();

        if (sellIn >= 6) {
            return;
        }
        incrementQuality();
    }
}
