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

    private String ladySwallowHorse() {
        return swallowAnimal("horse") + "...\n" +
                "...She's dead, of course!";
    }

    private String ladySwallowCow() {
        return swallowAnimal("cow") + ";\n" +
                "I don't know how she swallowed a cow!\n" +
                descAnimals("spider", "bird", "cat", "dog", "cow") +
                descSwallowFly() +
                ladyDead() +
                "\n";
    }

    private String descAnimals(String... animals) {
        StringBuilder sb = new StringBuilder();
        for (int i = animals.length - 1; i > 0; i--) {
            sb.append(descSwallowAnimal(animals[i - 1], animals[i]));
        }
        return sb.toString();
    }

    private String ladySwallowDog() {
        return swallowAnimal("dog") + ";\n" +
                "What a hog, to swallow a dog!\n" +
                descAnimals("spider", "bird", "cat", "dog") +
                descSwallowFly() +
                ladyDead() +
                "\n";
    }

    private String ladySwallowCat() {
        return swallowAnimal("cat") + ";\n" +
                "Fancy that to swallow a cat!\n" +
                descAnimals("spider", "bird", "cat") +
                descSwallowFly() +
                ladyDead() +
                "\n";
    }

    private String ladySwallowBird() {
        return swallowAnimal("bird") + ";\n" +
                "How absurd to swallow a bird.\n" +
                descAnimals("spider", "bird") +
                descSwallowFly() +
                ladyDead() +
                "\n";
    }

    private String ladySwallowSpider() {
        return swallowAnimal("spider") + ";\n" +
                "That wriggled and wiggled and tickled inside her.\n" +
                descSwallowFly() +
                ladyDead() +
                "\n";
    }

    private String descSwallowAnimal(String previousAnimal, String animal) {
        return "She swallowed the " + animal + " to catch the " + previousAnimal + ",\n";
    }

    private String descSwallowFly() {
        return "She swallowed the spider to catch the fly;\n";
    }

    private String ladySwallowFly() {
        return swallowAnimal("fly") + ".\n" +
                ladyDead() +
                "\n";
    }

    private String swallowAnimal(String animal) {
        return "There was an old lady who swallowed a " + animal;
    }

    private String ladyDead() {
        return "I don't know why she swallowed a fly - perhaps she'll die!\n";
    }
}