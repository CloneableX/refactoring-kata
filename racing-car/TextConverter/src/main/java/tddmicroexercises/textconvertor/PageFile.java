package tddmicroexercises.textconvertor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PageFile {
    private final File file;
    private final List<Integer> breaks;

    public PageFile(String filename) throws IOException {
        file = new File(filename);
        if (!file.exists()) {
            boolean isCreate = file.createNewFile();
            System.out.println("Create file " + file.getPath() + " " + isCreate);
        }

        this.breaks = new PageReader(file).getBreakLineCharsNumber();
    }

    public BufferedReader skipPage(int page) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.skip(breaks.get(page));

        return reader;
    }
}
