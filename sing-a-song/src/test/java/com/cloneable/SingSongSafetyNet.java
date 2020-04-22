package com.cloneable;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
        String result = Song.sing();

        byte[] buff = Files.readAllBytes(Paths.get("src/test/resources/baseline.txt"));
        String baseline = new String(buff, UTF_8);

        assertEquals(result, baseline);
    }
}
