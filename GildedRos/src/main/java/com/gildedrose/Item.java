package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    protected void updateQualityAfterSellIn() {
        decrementQuality();
    }

    private void decrementQuality() {
        if (quality > 0) {
            quality = quality - 1;
        }
    }

    protected void updateQuality() {
        decrementQuality();
    }

    protected void updateSellIn() {
        sellIn = sellIn - 1;
    }

    protected void incrementQuality() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }
}
