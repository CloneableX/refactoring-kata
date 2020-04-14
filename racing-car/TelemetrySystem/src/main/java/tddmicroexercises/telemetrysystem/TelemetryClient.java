package tddmicroexercises.telemetrysystem;

public class TelemetryClient {
    public static final String DIAGNOSTIC_MESSAGE = "AT#UD";

    private boolean onlineStatus;
    private String diagnosticMessageResult = "";

    private final TelemetrySimulator telemetrySimulator = new TelemetrySimulator(42);

    public boolean isOffline() {
        return !onlineStatus;
    }

    public void connect(String telemetryServerConnectionString) {
        if (StringUtils.isEmpty(telemetryServerConnectionString)) {
            throw new IllegalArgumentException();
        }

        onlineStatus = telemetrySimulator.simulateOnline();
    }

    public void disconnect() {
        diagnosticMessageResult = "";
        onlineStatus = false;
    }

    public void send(String message) {
        if (StringUtils.isEmpty(message)) {
            throw new IllegalArgumentException();
        }

        if (message.equals(DIAGNOSTIC_MESSAGE)) {
            diagnosticMessageResult = telemetrySimulator.simulateStatusMessage();
        }

        // here should go the real Send operation (not needed for this exercise)
    }

    public String receive() {
        if (StringUtils.isEmpty(diagnosticMessageResult)) {
            return telemetrySimulator.simulateMessage();
        }

        return diagnosticMessageResult;
    }

}

