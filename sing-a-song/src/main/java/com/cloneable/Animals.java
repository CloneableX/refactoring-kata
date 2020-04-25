package com.cloneable;

import java.util.Arrays;

public class Animals {
    private Animal[] animals;

    public Animals(Animal[] animals) {
        this.animals = animals;
    }

    private String catchAnimal(String previousAnimal, String animal) {
        return "She swallowed the " + animal + " to catch the " + previousAnimal;
    }

    public String catchAnimals(int animalIndex) {
        Animal[] caughtAnimals = Arrays.copyOf(this.animals, animalIndex + 1);
        StringBuilder sb = new StringBuilder();
        sb.append(animals[animalIndex].swallowed());
        for (int i = caughtAnimals.length - 1; i > 1; i--) {
            sb.append(catchAnimal(caughtAnimals[i - 1].getName(), caughtAnimals[i].getName()))
                    .append(",\n");
        }

        sb.append(catchAnimal(caughtAnimals[0].getName(), caughtAnimals[1].getName()))
                .append(";\n");
        return sb.toString();
    }
}
