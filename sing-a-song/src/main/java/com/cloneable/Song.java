package com.cloneable;

import java.util.Arrays;

class Song {

    private final Animal[] animals;
    private final OldLady oldLady;

    public Song() {
        animals = new Animal[]{
                new Animal("fly"),
                new Animal("spider", "That wriggled and wiggled and tickled inside her."),
                new Animal("bird", "How absurd to swallow a bird."),
                new Animal("cat", "Fancy that to swallow a cat!"),
                new Animal("dog", "What a hog, to swallow a dog!"),
                new Animal("cow", "I don't know how she swallowed a cow!"),
                new Animal("horse")
        };

        oldLady = new OldLady(animals);
    }

    public String sing() {
        return ladySwallowAnimals(animals);
    }

    private String ladySwallowAnimals(Animal[] animals) {
        StringBuilder sb = new StringBuilder();
        sb.append(oldLady.swallowFirstAnimal());
        for (int i = 1; i < animals.length - 1; i++) {
            sb.append(ladySwallowAnimal(animals[i], Arrays.copyOf(animals, i + 1)));
        }
        sb.append(ladySwallowLastAnimal(animals[animals.length - 1]));
        return sb.toString();
    }

    private String ladySwallowAnimal(Animal animal, Animal[] animals) {
        return animal.swallowed() +
                descAnimals(animals) +
                oldLady.perhapsDead() +
                "\n";
    }

    private String descAnimals(Animal... animals) {
        StringBuilder sb = new StringBuilder();
        for (int i = animals.length - 1; i > 1; i--) {
            sb.append(descSwallowAnimal(animals[i - 1].getName(), animals[i].getName()))
                    .append(",\n");
        }

        sb.append(descSwallowAnimal(animals[0].getName(), animals[1].getName()))
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

}