package com.gildedrose.item;

import com.gildedrose.Item;

public class ArgedBrie extends Item {
    public ArgedBrie(int sellIn, int quality) {
        super("Aged Brie", sellIn, quality);
    }

    @Override
    protected boolean isArgedBrie() {
        return true;
    }
}
