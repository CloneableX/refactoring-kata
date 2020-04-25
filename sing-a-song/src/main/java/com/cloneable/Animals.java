package com.cloneable;

import java.util.Arrays;

public class Animals {
    private Animal[] animals;

    public Animals(Animal[] animals) {
        this.animals = animals;
    }

    private String catchAnimal(Animal previousAnimal, Animal animal) {
        return "She swallowed the " + animal.getName() + " to catch the " + previousAnimal.getName();
    }

    public String catchAnimals(int animalIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append(animals[animalIndex].swallowed());

        Animal[] caughtAnimals = Arrays.copyOf(this.animals, animalIndex + 1);
        for (int i = caughtAnimals.length - 1; i > 1; i--) {
            sb.append(catchAnimal(caughtAnimals[i - 1], caughtAnimals[i]))
                    .append(",\n");
        }

        sb.append(catchAnimal(caughtAnimals[0], caughtAnimals[1]))
                .append(";\n");
        return sb.toString();
    }

    public Animal first() {
        return animals[0];
    }

    public Animal last() {
        return animals[animals.length - 1];
    }

    public int animalsSize() {
        return animals.length;
    }
}
