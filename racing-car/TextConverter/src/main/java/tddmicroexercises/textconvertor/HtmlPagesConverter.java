package tddmicroexercises.textconvertor;

import java.io.BufferedReader;
import java.io.IOException;

public class HtmlPagesConverter extends HtmlTextConverter {

    private final PageFile pageFile;

    public HtmlPagesConverter(String fullFilenameWithPath) throws IOException {
        super(fullFilenameWithPath);
        this.pageFile = new PageFile(fullFilenameWithPath);
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

}
