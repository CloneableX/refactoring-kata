package com.cloneable;

import java.util.Arrays;

public class OldLady {
    private Animal[] animals;

    public OldLady(Animal[] animals) {
        this.animals = animals;
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

    public String descSwallowAnimal(String previousAnimal, String animal) {
        return "She swallowed the " + animal + " to catch the " + previousAnimal;
    }

    public String descAnimals(Animal... animals) {
        StringBuilder sb = new StringBuilder();
        for (int i = animals.length - 1; i > 1; i--) {
            sb.append(descSwallowAnimal(animals[i - 1].getName(), animals[i].getName()))
                    .append(",\n");
        }

        sb.append(descSwallowAnimal(animals[0].getName(), animals[1].getName()))
                .append(";\n");
        return sb.toString();
    }

    private String swallowAnimal(int animalIndex) {
        return animals[animalIndex].swallowed() +
                descAnimals(Arrays.copyOf(animals, animalIndex + 1)) +
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
