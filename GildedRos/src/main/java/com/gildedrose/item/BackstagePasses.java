package com.gildedrose.item;

import com.gildedrose.Item;

public class BackstagePasses extends Item {
    public BackstagePasses(int sellIn, int quality) {
        super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    @Override
    protected boolean isBackstagePasses() {
        return true;
    }
}
