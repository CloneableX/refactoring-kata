package com.cloneable;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SingSongSafetyNet {
    @Test
    public void safe_net() throws IOException {
        Animal[] animals = new Animal[]{
                new Animal("fly"),
                new Animal("spider", "That wriggled and wiggled and tickled inside her."),
                new Animal("bird", "How absurd to swallow a bird."),
                new Animal("cat", "Fancy that to swallow a cat!"),
                new Animal("dog", "What a hog, to swallow a dog!"),
                new Animal("cow", "I don't know how she swallowed a cow!"),
                new Animal("horse")
        };
        String result = new Song(animals).sing();

        byte[] buff = Files.readAllBytes(Paths.get("src/test/resources/baseline.txt"));
        String baseline = new String(buff, UTF_8);

        assertEquals(result, baseline);
    }
}
