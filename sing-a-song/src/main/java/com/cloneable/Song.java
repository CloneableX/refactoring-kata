package com.cloneable;

import java.util.Arrays;
import java.util.stream.Collectors;

class Song {

    public String sing() {
        Animal fly = new Animal("fly");
        Animal spider = new Animal("spider", "That wriggled and wiggled and tickled inside her.");
        Animal bird = new Animal("bird", "How absurd to swallow a bird.");
        Animal cat = new Animal("cat", "Fancy that to swallow a cat!");
        Animal dog = new Animal("dog", "What a hog, to swallow a dog!");
        Animal cow = new Animal("cow", "I don't know how she swallowed a cow!");
        Animal horse = new Animal("horse");

        return ladySwallowFirstAnimal(fly) +
                ladySwallowAnimal(spider, new Animal[]{fly, spider}) +
                ladySwallowAnimal(bird, new Animal[]{fly, spider, bird}) +
                ladySwallowAnimal(cat, new Animal[]{fly, spider, bird, cat}) +
                ladySwallowAnimal(dog, new Animal[]{fly, spider, bird, cat, dog}) +
                ladySwallowAnimal(cow, new Animal[]{fly, spider, bird, cat, dog, cow}) +
                ladySwallowLastAnimal(horse);
    }

    private String ladySwallowAnimal(Animal animal, Animal[] animals) {
        String[] animalNames = new String[animals.length];
        Arrays.stream(animals)
                .map(Animal::getName)
                .collect(Collectors.toList())
                .toArray(animalNames);

        return animal.swallowed() +
                descAnimals(animalNames) +
                ladyDead() +
                "\n";
    }

    private String descAnimals(String... animals) {
        StringBuilder sb = new StringBuilder();
        for (int i = animals.length - 1; i > 1; i--) {
            sb.append(descSwallowAnimal(animals[i - 1], animals[i]))
                    .append(",\n");
        }

        sb.append(descSwallowAnimal(animals[0], animals[1]))
                .append(";\n");
        return sb.toString();
    }

    private String ladySwallowLastAnimal(Animal animal) {
        return animal.swallowed() + "...\n" +
                "...She's dead, of course!";
    }

    private String descSwallowAnimal(String previousAnimal, String animal) {
        return "She swallowed the " + animal + " to catch the " + previousAnimal;
    }

    private String ladySwallowFirstAnimal(Animal animal) {
        return animal.swallowed() + ".\n" +
                ladyDead() +
                "\n";
    }

    private String ladyDead() {
        return "I don't know why she swallowed a fly - perhaps she'll die!\n";
    }
}