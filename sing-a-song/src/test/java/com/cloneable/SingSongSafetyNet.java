package com.cloneable;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class SingSongSafetyNet {
    public static final String TEST_FILE_PATH = "src/test/resources/";

    private Animal[] animals = new Animal[]{
            new Animal("fly"),
            new Animal("spider", "That wriggled and wiggled and tickled inside her."),
            new Animal("bird", "How absurd to swallow a bird."),
            new Animal("cat", "Fancy that to swallow a cat!"),
            new Animal("dog", "What a hog, to swallow a dog!"),
            new Animal("cow", "I don't know how she swallowed a cow!"),
            new Animal("horse")
    };

    @Test
    public void safe_net() throws IOException {
        String result = new Song(animals).buildLyrics();

        byte[] buff = readBaseLineBytes();
        String baseline = new String(buff, UTF_8);

        assertEquals(result, baseline);
    }

    @Test
    public void should_sing_lyrics_when_give_animal_list() throws IOException {
        String pathname = TEST_FILE_PATH + "result.txt";
        PrintStream printStream = new PrintStream(new File(pathname));
        System.setOut(printStream);

        new Song(animals).sing();

        byte[] buff = Files.readAllBytes(Paths.get(pathname));
        byte[] expectedBuff = readBaseLineBytes();
        assertEquals(new String(buff, UTF_8), new String(expectedBuff, UTF_8));
    }

    private byte[] readBaseLineBytes() throws IOException {
        return Files.readAllBytes(Paths.get(TEST_FILE_PATH + "baseline.txt"));
    }
}
