package com.cloneable;

public class OldLady {
    private final Animals animals;

    public OldLady(Animal[] animals) {
        this.animals = new Animals(animals);
    }

    private String perhapsDead() {
        return "I don't know why she swallowed a " + animals.first().getName() + " - perhaps she'll die!\n";
    }

    private String swallowFirstAnimal() {
        return animals.first().swallowed() + ".\n" +
                perhapsDead() +
                "\n";
    }

    private String swallowLastAnimal() {
        return animals.last().swallowed() + "...\n" +
                "...She's dead, of course!";
    }

    private String swallowAnimal(int animalIndex) {
        return animals.catchAnimals(animalIndex) +
                perhapsDead() +
                "\n";
    }

    public String swallowAnimals() {
        StringBuilder sb = new StringBuilder();
        sb.append(swallowFirstAnimal());
        for (int i = 1; i < animals.animalsSize() - 1; i++) {
            sb.append(swallowAnimal(i));
        }
        sb.append(swallowLastAnimal());
        return sb.toString();
    }

}
