package tddmicroexercises.textconvertor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;


public class HtmlPagesConverterTest {
    public static final String PATH_PREFIX = "src/test/resources/";
    public static final String SOURCE_FILE = "text_page.txt";

    @Test
    public void test_get_file_name_when_initialize_file_name() throws IOException {
        HtmlPagesConverter converter = new HtmlPagesConverter(SOURCE_FILE);
        assertEquals(SOURCE_FILE, converter.getFilename());
    }

    @Test
    public void should_get_page_html_when_text_with_page_break() throws IOException {
        HtmlPagesConverter converter = new HtmlPagesConverter(PATH_PREFIX + SOURCE_FILE);

        byte[] buff = Files.readAllBytes(Paths.get(PATH_PREFIX + "html_page_convert_result.html"));
        String expectedString = new String(buff, Charset.defaultCharset());

        assertThat(converter.getHtmlPage(1), is(expectedString));
    }
}
