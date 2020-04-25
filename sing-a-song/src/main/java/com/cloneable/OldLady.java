package com.cloneable;

public class OldLady {
    private Animal[] animals;

    public OldLady(Animal[] animals) {
        this.animals = animals;
    }

    public String perhapsDead() {
        return "I don't know why she swallowed a " + animals[0].getName() + " - perhaps she'll die!\n";
    }
}
