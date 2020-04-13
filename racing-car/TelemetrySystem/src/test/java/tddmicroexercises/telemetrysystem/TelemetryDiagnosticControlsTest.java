package tddmicroexercises.telemetrysystem;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TelemetryDiagnosticControlsTest
{
    
	@Test
    public void CheckTransmission_should_send_a_diagnostic_message_and_receive_a_status_message_response() throws Exception {
        TelemetryDiagnosticControls controls = new TelemetryDiagnosticControls();
        String response = controls.checkTransmission();

        byte[] buff = Files.readAllBytes(Paths.get("src/test/resources/response.txt"));
        String expected = new String(buff, Charset.defaultCharset());

        assertThat(response, is(expected));
    }

}
