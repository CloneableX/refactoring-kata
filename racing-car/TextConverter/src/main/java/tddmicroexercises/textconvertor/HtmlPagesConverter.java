package tddmicroexercises.textconvertor;

import java.io.IOException;

public class HtmlPagesConverter extends HtmlTextConverter {

    private final PageFile pageFile;

    public HtmlPagesConverter(String fullFilenameWithPath) throws IOException {
        super(fullFilenameWithPath);
        this.pageFile = new PageFile(fullFilenameWithPath);
    }

    public String getHtmlPage(int page) throws IOException {
        return convertToHtml(pageFile.skipPage(page));
    }

    @Override
    public boolean isFileEnd(String line) {
        return line != null && !PageReader.isBreakLine(line);
    }

}
