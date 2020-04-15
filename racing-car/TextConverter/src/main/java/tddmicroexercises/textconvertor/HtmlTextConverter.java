package tddmicroexercises.textconvertor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HtmlTextConverter {
    private String fullFilenameWithPath;

    public HtmlTextConverter(String fullFilenameWithPath) {
        this.fullFilenameWithPath = fullFilenameWithPath;
    }

    public String convertToHtml() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fullFilenameWithPath));

        String line = reader.readLine();
        StringBuilder html = new StringBuilder();
        while (line != null) {
            html.append(StringEscapeUtils.escapeHtml(line));
            html.append("<br />");
            line = reader.readLine();
        }
        return html.toString();

    }

    public String getFilename() {
        return this.fullFilenameWithPath;
    }
}
