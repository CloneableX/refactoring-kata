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

        ByteOutputStream out = new ByteOutputStream();
        PrintStream printStream = new PrintStream(out);
        printStream.println(song);

        return out.toString();
    }

    private String ladySwallowHorse() {
        return swallowAnimal("horse") + "...\n" +
                "...She's dead, of course!";
    }

    private String ladySwallowCow() {
        return swallowAnimal("cow") + ";\n" +
                "I don't know how she swallowed a cow!\n" +
                "She swallowed the cow to catch the dog,\n" +
                "She swallowed the dog to catch the cat,\n" +
                "She swallowed the cat to catch the bird,\n" +
                "She swallowed the bird to catch the spider,\n" +
                "She swallowed the spider to catch the fly;\n" +
                ladyDead() +
                "\n";
    }

    private String ladySwallowDog() {
        return swallowAnimal("dog") + ";\n" +
                "What a hog, to swallow a dog!\n" +
                "She swallowed the dog to catch the cat,\n" +
                "She swallowed the cat to catch the bird,\n" +
                "She swallowed the bird to catch the spider,\n" +
                "She swallowed the spider to catch the fly;\n" +
                ladyDead() +
                "\n";
    }

    private String ladySwallowCat() {
        return swallowAnimal("cat") + ";\n" +
                "Fancy that to swallow a cat!\n" +
                "She swallowed the cat to catch the bird,\n" +
                "She swallowed the bird to catch the spider,\n" +
                "She swallowed the spider to catch the fly;\n" +
                ladyDead() +
                "\n";
    }

    private String ladySwallowBird() {
        return swallowAnimal("bird") + ";\n" +
                "How absurd to swallow a bird.\n" +
                "She swallowed the bird to catch the spider,\n" +
                "She swallowed the spider to catch the fly;\n" +
                ladyDead() +
                "\n";
    }

    private String ladySwallowSpider() {
        return swallowAnimal("spider") + ";\n" +
                "That wriggled and wiggled and tickled inside her.\n" +
                "She swallowed the spider to catch the fly;\n" +
                ladyDead() +
                "\n";
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