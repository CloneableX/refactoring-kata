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

    public String simulateStatusMessage() {
        return "LAST TX rate................ 100 MBPS\r\n"
                + "HIGHEST TX rate............. 100 MBPS\r\n"
                + "LAST RX rate................ 100 MBPS\r\n"
                + "HIGHEST RX rate............. 100 MBPS\r\n"
                + "BIT RATE.................... 100000000\r\n"
                + "WORD LEN.................... 16\r\n"
                + "WORD/FRAME.................. 511\r\n"
                + "BITS/FRAME.................. 8192\r\n"
                + "MODULATION TYPE............. PCM/FM\r\n"
                + "TX Digital Los.............. 0.75\r\n"
                + "RX Digital Los.............. 0.10\r\n"
                + "BEP Test.................... -5\r\n"
                + "Local Rtrn Count............ 00\r\n"
                + "Remote Rtrn Count........... 00";
    }
}
