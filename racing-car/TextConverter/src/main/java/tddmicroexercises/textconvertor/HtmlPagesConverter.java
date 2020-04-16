package tddmicroexercises.textconvertor;

import java.io.BufferedReader;
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
        this.pageFile = new PageFile(filename);
        this.breaks = pageFile.markBreakLine();
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

    public static boolean isBreakLine(String line) {
        return line.contains("PAGE_BREAK");
    }

    public String getFilename() {
        return this.filename;
    }

}
