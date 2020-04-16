package tddmicroexercises.textconvertor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageFile {
    private final File file;

    public PageFile(String filename) throws IOException {
        file = new File(filename);
        if (!file.exists()) {
            boolean isCreate = file.createNewFile();
            System.out.println("Create file " + file.getPath() + " " + isCreate);
        }
    }

    public List<Integer> markBreakLine() throws IOException {
        List<Integer> breaks = new ArrayList<>();
        breaks.add(0);

        BufferedReader reader = new BufferedReader(new FileReader(file));
        int cumulativeCharCount = 0;
        String line = reader.readLine();
        while (line != null) {
            cumulativeCharCount += line.length() + 1; // add one for the newline
            if (HtmlPagesConverter.isBreakLine(line)) {
                breaks.add(cumulativeCharCount);
            }
            line = reader.readLine();
        }
        reader.close();

        return breaks;
    }
}
