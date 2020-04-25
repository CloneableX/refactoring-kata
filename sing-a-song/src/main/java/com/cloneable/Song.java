package com.cloneable;

class Song {

    public String sing() {

        return ladySwallowFirstAnimal(new Animal("fly")) +
                ladySwallowAnimal(new Animal("spider", "That wriggled and wiggled and tickled inside her."), new String[]{"fly", "spider"}) +
                ladySwallowAnimal(new Animal("bird", "How absurd to swallow a bird."), new String[]{"fly", "spider", "bird"}) +
                ladySwallowAnimal(new Animal("cat", "Fancy that to swallow a cat!"), new String[]{"fly", "spider", "bird", "cat"}) +
                ladySwallowAnimal(new Animal("dog", "What a hog, to swallow a dog!"), new String[]{"fly", "spider", "bird", "cat", "dog"}) +
                ladySwallowAnimal(new Animal("cow", "I don't know how she swallowed a cow!"), new String[]{"fly", "spider", "bird", "cat", "dog", "cow"}) +
                ladySwallowLastAnimal(new Animal("horse"));
    }

    private String ladySwallowAnimal(Animal animal, String[] animals) {
        return animal.swallowed() +
                descAnimals(animals) +
                ladyDead() +
                "\n";
    }

    private String descAnimals(String... animals) {
        StringBuilder sb = new StringBuilder();
        for (int i = animals.length - 1; i > 1; i--) {
            sb.append(descSwallowAnimal(animals[i - 1], animals[i]))
                    .append(",\n");
        }

        sb.append(descSwallowAnimal(animals[0], animals[1]))
                .append(";\n");
        return sb.toString();
    }

    private String ladySwallowLastAnimal(Animal animal) {
        return swallowAnimal(animal) + "...\n" +
                "...She's dead, of course!";
    }

    private String descSwallowAnimal(String previousAnimal, String animal) {
        return "She swallowed the " + animal + " to catch the " + previousAnimal;
    }

    private String ladySwallowFirstAnimal(Animal animal) {
        return swallowAnimal(animal) + ".\n" +
                ladyDead() +
                "\n";
    }

    public static String swallowAnimal(Animal animal) {
        return "There was an old lady who swallowed a " + animal.getName();
    }

    private String ladyDead() {
        return "I don't know why she swallowed a fly - perhaps she'll die!\n";
    }
}