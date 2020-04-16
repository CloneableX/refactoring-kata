package tddmicroexercises.textconvertor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlPagesConverter {

    private final PageFile pageFile;
    private String filename;
    private List<Integer> breaks = new ArrayList<>();

    public HtmlPagesConverter(String filename) throws IOException {
        this.filename = filename;
        this.breaks.add(0);

        pageFile = new PageFile(filename);

        markBreakLine();
    }

    private void markBreakLine() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        int cumulativeCharCount = 0;
        String line = reader.readLine();
        while (line != null) {
            cumulativeCharCount += line.length() + 1; // add one for the newline
            markBreakLineChar(cumulativeCharCount, line);
            line = reader.readLine();
        }
        reader.close();
    }

    private void markBreakLineChar(int cumulativeCharCount, String line) {
        if (isBreakLine(line)) {
            breaks.add(cumulativeCharCount);
        }
    }

    public String getHtmlPage(int page) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        reader.skip(breaks.get(page));
        StringBuilder htmlPage = new StringBuilder();
        String line = reader.readLine();
        while (line != null && !isBreakLine(line)) {
            htmlPage.append(StringEscapeUtils.escapeHtml(line));
            htmlPage.append("<br />");

            line = reader.readLine();
        }
        reader.close();
        return htmlPage.toString();
    }

    private boolean isBreakLine(String line) {
        return line.contains("PAGE_BREAK");
    }

    public String getFilename() {
        return this.filename;
    }

}
