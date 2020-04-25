package com.cloneable;

class Song {

    public String sing() {

        return ladySwallowFly() +
                ladySwallowSpider() +
                ladySwallowBird() +
                ladySwallowCat() +
                ladySwallowDog() +
                ladySwallowCow() +
                ladySwallowHorse();
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

    private String ladySwallowHorse() {
        return swallowAnimal("horse") + "...\n" +
                "...She's dead, of course!";
    }

    private String ladySwallowCow() {
        return swallowAnimal("cow", "I don't know how she swallowed a cow!") +
                descAnimals("fly", "spider", "bird", "cat", "dog", "cow") +
                ladyDead() +
                "\n";
    }

    private String ladySwallowDog() {
        return swallowAnimal("dog", "What a hog, to swallow a dog!") +
                descAnimals("fly", "spider", "bird", "cat", "dog") +
                ladyDead() +
                "\n";
    }

    private String ladySwallowCat() {
        return swallowAnimal("cat", "Fancy that to swallow a cat!") +
                descAnimals("fly", "spider", "bird", "cat") +
                ladyDead() +
                "\n";
    }

    private String ladySwallowBird() {
        return swallowAnimal("bird", "How absurd to swallow a bird.") +
                descAnimals("fly", "spider", "bird") +
                ladyDead() +
                "\n";
    }

    private String ladySwallowSpider() {
        return swallowAnimal("spider", "That wriggled and wiggled and tickled inside her.") +
                descAnimals("fly", "spider") +
                ladyDead() +
                "\n";
    }

    private String descSwallowAnimal(String previousAnimal, String animal) {
        return "She swallowed the " + animal + " to catch the " + previousAnimal;
    }

    private String ladySwallowFly() {
        return swallowAnimal("fly") + ".\n" +
                ladyDead() +
                "\n";
    }

    private String swallowAnimal(String animal) {
        return "There was an old lady who swallowed a " + animal;
    }

    private String swallowAnimal(String animal, String description) {
        return swallowAnimal(animal) + ";\n" +
                description + "\n";
    }

    private String ladyDead() {
        return "I don't know why she swallowed a fly - perhaps she'll die!\n";
    }
}