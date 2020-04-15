package tddmicroexercises.textconvertor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlTextConverterTest {

    public static final String PATH_PREFIX = "src/test/resources/";
    public static final String SOURCE_FILE = "text.txt";

    @Test
    public void should_get_file_name_when_initialize_html_file_path() {
        HtmlTextConverter converter = new HtmlTextConverter(SOURCE_FILE);
        assertEquals(SOURCE_FILE, converter.getFilename());
    }

    @Test
    public void should_get_html_string_when_convert_text_to_html() throws IOException {
        HtmlTextConverter converter = new HtmlTextConverter(PATH_PREFIX + SOURCE_FILE);

        byte[] buff = Files.readAllBytes(Paths.get(PATH_PREFIX + "html_text_convert_result.html"));
        String expectedString = new String(buff, Charset.defaultCharset());

        assertThat(converter.convertToHtml(), is(expectedString));
    }
}
