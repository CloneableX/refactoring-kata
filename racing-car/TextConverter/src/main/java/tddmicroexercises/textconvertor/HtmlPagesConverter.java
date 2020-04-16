package tddmicroexercises.textconvertor;

import java.io.BufferedReader;
import java.io.IOException;

public class HtmlPagesConverter {

    private final PageFile pageFile;
    private String filename;

    public HtmlPagesConverter(String filename) throws IOException {
        this.filename = filename;
        this.pageFile = new PageFile(filename);
    }

    public String getHtmlPage(int page) throws IOException {
        BufferedReader reader = pageFile.skipPage(page);
        StringBuilder htmlPage = new StringBuilder();
        String line = reader.readLine();
        while (line != null && !PageReader.isBreakLine(line)) {
            htmlPage.append(StringEscapeUtils.escapeHtml(line));
            htmlPage.append("<br />");

            line = reader.readLine();
        }
        reader.close();
        return htmlPage.toString();
    }

    public String getFilename() {
        return this.filename;
    }

}
