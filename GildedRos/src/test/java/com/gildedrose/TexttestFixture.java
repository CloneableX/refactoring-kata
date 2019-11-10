package com.gildedrose;

import com.gildedrose.item.ArgedBrie;
import com.gildedrose.item.BackstagePasses;
import com.gildedrose.item.Sulfuras;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.PrintStream;

public class TexttestFixture {

    public static void main(String[] args) {
        getBaseline();
    }

    public static String getBaseline() {
        ByteOutputStream out = new ByteOutputStream();
        PrintStream printStream = new PrintStream(out);
        printStream.println("OMGHAI!");

        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20), //
                new ArgedBrie(2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Sulfuras(0, 80), //
                new Sulfuras(-1, 80),
                new BackstagePasses(15, 20),
                new BackstagePasses(10, 49),
                new BackstagePasses(5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6)};

        GildedRose app = new GildedRose(items);

        int days = 2;

        for (int i = 0; i < days; i++) {
            printStream.println("-------- day " + i + " --------");
            printStream.println("name, sellIn, quality");
            for (Item item : items) {
                printStream.println(item);
            }
            printStream.println();
            app.updateQuality();
        }

        return out.toString();
    }

}
