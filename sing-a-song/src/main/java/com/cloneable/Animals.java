package com.cloneable;

public class Animals {
    private Animal[] animals;

    public Animals(Animal[] animals) {
        this.animals = animals;
    }

    private String catchAnimal(String previousAnimal, String animal) {
        return "She swallowed the " + animal + " to catch the " + previousAnimal;
    }

    public String catchAnimals(Animal... animals) {
        StringBuilder sb = new StringBuilder();
        for (int i = animals.length - 1; i > 1; i--) {
            sb.append(catchAnimal(animals[i - 1].getName(), animals[i].getName()))
                    .append(",\n");
        }

        sb.append(catchAnimal(animals[0].getName(), animals[1].getName()))
                .append(";\n");
        return sb.toString();
    }
}
