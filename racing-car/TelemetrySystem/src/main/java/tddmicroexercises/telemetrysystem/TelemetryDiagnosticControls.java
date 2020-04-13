package tddmicroexercises.telemetrysystem;

public class TelemetryDiagnosticControls {

    private final TelemetryClient telemetryClient;

    public TelemetryDiagnosticControls() {
        telemetryClient = new TelemetryClient();
    }

    public String checkTransmission() throws Exception {

        telemetryClient.disconnect();

        int retryTimes = 0;
        while (telemetryClient.isOffline() && retryTimes < 3) {
            telemetryClient.connect("*111#");
            retryTimes++;
        }

        if (telemetryClient.isOffline()) {
            throw new Exception("Unable to connect.");
        }

        telemetryClient.send(TelemetryClient.DIAGNOSTIC_MESSAGE);
        return telemetryClient.receive();
    }
}
