package tddmicroexercises.telemetrysystem;

import java.util.Random;

public class TelemetrySimulator {
    private final Random simulator;

    public TelemetrySimulator(int randomSeed) {
        simulator = new Random(randomSeed);
    }

    public String simulateMessage() {
        // simulate a received message (just for illustration - not needed for this exercise)
        StringBuilder message = new StringBuilder();
        int messageLength = simulator.nextInt(50) + 60;
        for (int i = messageLength; i >= 0; --i) {
            message.append((char) simulator.nextInt(40) + 86);
        }
        return message.toString();
    }

    public boolean simulateOnline() {
        return simulator.nextInt(10) <= 8;
    }
}
