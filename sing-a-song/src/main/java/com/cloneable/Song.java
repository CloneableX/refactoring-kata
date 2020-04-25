package com.cloneable;

class Song {

    private final OldLady oldLady;

    public Song(Animal[] animals) {
        oldLady = new OldLady(animals);
    }

    public String sing() {
        return oldLady.ladySwallowAnimals();
    }

}