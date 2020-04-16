package tddmicroexercises.textconvertor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PageReader {
    private File file;

    public PageReader(File file) {
        this.file = file;
    }

    public List<Integer> markBreakLine() throws IOException {
        List<Integer> breaks = new ArrayList<>();
        breaks.add(0);

        BufferedReader reader = new BufferedReader(new FileReader(file));
        int cumulativeCharCount = 0;
        String line = reader.readLine();
        while (line != null) {
            cumulativeCharCount += line.length() + 1; // add one for the newline
            if (PageFile.isBreakLine(line)) {
                breaks.add(cumulativeCharCount);
            }
            line = reader.readLine();
        }
        reader.close();

        return breaks;
    }
}
