package com.cloneable;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.PrintStream;

class Song {

    public static String sing() {

        String song = ladySwallowFly() +
                ladySwallowSpider() +
                ladySwallowBird() +
                ladySwallowCat() +
                ladySwallowDog() +
                ladySwallowCow() +
                ladySwallowHorse();

        ByteOutputStream out = new ByteOutputStream();
        PrintStream printStream = new PrintStream(out);
        printStream.println(song);

        return out.toString();
    }

    private static String ladySwallowHorse() {
        return "There was an old lady who swallowed a horse...\n" +
                "...She's dead, of course!";
    }

    private static String ladySwallowCow() {
        return "There was an old lady who swallowed a cow;\n" +
                "I don't know how she swallowed a cow!\n" +
                "She swallowed the cow to catch the dog,\n" +
                "She swallowed the dog to catch the cat,\n" +
                "She swallowed the cat to catch the bird,\n" +
                "She swallowed the bird to catch the spider,\n" +
                "She swallowed the spider to catch the fly;\n" +
                "I don't know why she swallowed a fly - perhaps she'll die!\n" +
                "\n";
    }

    private static String ladySwallowDog() {
        return "There was an old lady who swallowed a dog;\n" +
                "What a hog, to swallow a dog!\n" +
                "She swallowed the dog to catch the cat,\n" +
                "She swallowed the cat to catch the bird,\n" +
                "She swallowed the bird to catch the spider,\n" +
                "She swallowed the spider to catch the fly;\n" +
                "I don't know why she swallowed a fly - perhaps she'll die!\n" +
                "\n";
    }

    private static String ladySwallowCat() {
        return "There was an old lady who swallowed a cat;\n" +
                "Fancy that to swallow a cat!\n" +
                "She swallowed the cat to catch the bird,\n" +
                "She swallowed the bird to catch the spider,\n" +
                "She swallowed the spider to catch the fly;\n" +
                "I don't know why she swallowed a fly - perhaps she'll die!\n" +
                "\n";
    }

    private static String ladySwallowBird() {
        return "There was an old lady who swallowed a bird;\n" +
                "How absurd to swallow a bird.\n" +
                "She swallowed the bird to catch the spider,\n" +
                "She swallowed the spider to catch the fly;\n" +
                "I don't know why she swallowed a fly - perhaps she'll die!\n" +
                "\n";
    }

    private static String ladySwallowSpider() {
        return "There was an old lady who swallowed a spider;\n" +
                "That wriggled and wiggled and tickled inside her.\n" +
                "She swallowed the spider to catch the fly;\n" +
                "I don't know why she swallowed a fly - perhaps she'll die!\n" +
                "\n";
    }

    private static String ladySwallowFly() {
        return "There was an old lady who swallowed a fly.\n" +
                "I don't know why she swallowed a fly - perhaps she'll die!\n" +
                "\n";
    }
}