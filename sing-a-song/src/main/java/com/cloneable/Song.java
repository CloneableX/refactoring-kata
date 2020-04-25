package com.cloneable;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.PrintStream;

class Song {

    public String sing() {

        String song = ladySwallowFly() +
                ladySwallowSpider() +
                ladySwallowBird() +
                ladySwallowCat() +
                ladySwallowDog() +
                ladySwallowCow() +
                ladySwallowHorse();

        return song;
    }

    private String ladySwallowHorse() {
        return swallowAnimal("horse") + "...\n" +
                "...She's dead, of course!";
    }

    private String ladySwallowCow() {
        return swallowAnimal("cow") + ";\n" +
                "I don't know how she swallowed a cow!\n" +
                descSwallowDog() +
                descSwallowCat() +
                descSwallowBird() +
                descSwallowSpider() +
                descSwallowFly() +
                ladyDead() +
                "\n";
    }

    private String ladySwallowDog() {
        return swallowAnimal("dog") + ";\n" +
                "What a hog, to swallow a dog!\n" +
                descSwallowCat() +
                descSwallowBird() +
                descSwallowSpider() +
                descSwallowFly() +
                ladyDead() +
                "\n";
    }

    private String ladySwallowCat() {
        return swallowAnimal("cat") + ";\n" +
                "Fancy that to swallow a cat!\n" +
                descSwallowBird() +
                descSwallowSpider() +
                descSwallowFly() +
                ladyDead() +
                "\n";
    }

    private String ladySwallowBird() {
        return swallowAnimal("bird") + ";\n" +
                "How absurd to swallow a bird.\n" +
                descSwallowSpider() +
                descSwallowFly() +
                ladyDead() +
                "\n";
    }

    private String ladySwallowSpider() {
        return swallowAnimal("spider") + ";\n" +
                "That wriggled and wiggled and tickled inside her.\n" +
                descSwallowFly() +
                ladyDead() +
                "\n";
    }

    private String descSwallowDog() {
        return "She swallowed the cow to catch the dog,\n";
    }

    private String descSwallowCat() {
        return "She swallowed the dog to catch the cat,\n";
    }

    private String descSwallowBird() {
        return "She swallowed the cat to catch the bird,\n";
    }

    private String descSwallowSpider() {
        return "She swallowed the bird to catch the spider,\n";
    }

    private String descSwallowFly() {
        return "She swallowed the spider to catch the fly;\n";
    }

    private String ladySwallowFly() {
        return swallowAnimal("fly") + ".\n" +
                ladyDead() +
                "\n";
    }

    private String swallowAnimal(String animal) {
        return "There was an old lady who swallowed a " + animal;
    }

    private String ladyDead() {
        return "I don't know why she swallowed a fly - perhaps she'll die!\n";
    }
}