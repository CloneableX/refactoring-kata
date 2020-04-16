package tddmicroexercises.textconvertor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HtmlTextConverter {
    private String fullFilenameWithPath;

    public HtmlTextConverter(String fullFilenameWithPath) {
        this.fullFilenameWithPath = fullFilenameWithPath;
    }

    public String getHtmlText() throws IOException {
        return convertToHtml(new BufferedReader(new FileReader(fullFilenameWithPath)));
    }

    public String convertToHtml(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        StringBuilder html = new StringBuilder();
        while (isFileEnd(line)) {
            html.append(StringEscapeUtils.escapeHtml(line));
            html.append("<br />");
            line = reader.readLine();
        }
        reader.close();
        return html.toString();
    }

    protected boolean isFileEnd(String line) {
        return line != null;
    }

    public String getFilename() {
        return this.fullFilenameWithPath;
    }
}
