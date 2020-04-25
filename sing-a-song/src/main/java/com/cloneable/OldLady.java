package com.cloneable;

import java.util.Arrays;

public class OldLady {
    private final Animals animalsTemp;
    private Animal[] animals;

    public OldLady(Animal[] animals) {
        this.animals = animals;
        this.animalsTemp = new Animals(animals);
    }

    public String perhapsDead() {
        return "I don't know why she swallowed a " + animals[0].getName() + " - perhaps she'll die!\n";
    }

    public String swallowFirstAnimal() {
        return animals[0].swallowed() + ".\n" +
                perhapsDead() +
                "\n";
    }

    public String swallowLastAnimal() {
        return animals[animals.length - 1].swallowed() + "...\n" +
                "...She's dead, of course!";
    }

    private String swallowAnimal(int animalIndex) {
        return animals[animalIndex].swallowed() +
                animalsTemp.catchAnimals(Arrays.copyOf(animals, animalIndex + 1)) +
                perhapsDead() +
                "\n";
    }

    public String swallowAnimals() {
        StringBuilder sb = new StringBuilder();
        sb.append(swallowFirstAnimal());
        for (int i = 1; i < animals.length - 1; i++) {
            sb.append(swallowAnimal(i));
        }
        sb.append(swallowLastAnimal());
        return sb.toString();
    }
}
