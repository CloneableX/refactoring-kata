package tddmicroexercises.textconvertor;

import java.io.File;
import java.io.IOException;

public class PageFile {
    private final File file;

    public PageFile(String filename) throws IOException {
        file = new File(filename);
        if (!file.exists()) {
            boolean isCreate = file.createNewFile();
            System.out.println("Create file " + file.getPath() + " " + isCreate);
        }
    }
}
