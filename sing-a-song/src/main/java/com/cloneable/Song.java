package com.cloneable;

class Song {

    private final OldLady oldLady;

    public Song(Animal[] animals) {
        oldLady = new OldLady(animals);
    }

    public void sing() {
        System.out.print(buildLyrics());
    }

    public String buildLyrics() {
        return oldLady.swallowAnimals();
    }

}