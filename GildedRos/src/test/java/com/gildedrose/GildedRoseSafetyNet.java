package com.gildedrose;

import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.google.common.base.Charsets.UTF_8;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GildedRoseSafetyNet {
    @Test
    public void should_always_align_with_baseline() throws IOException {
        String output = TexttestFixture.getBaseline();
        String baseline = Files.toString(new File("src/test/baseline.txt"), UTF_8);
        assertThat(output, is(baseline));
    }
}
