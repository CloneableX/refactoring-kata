package tddmicroexercises.tirepressuremonitoringsystem;

public class SensorMock extends Sensor {
    private double mockNextPressurePsiValue;

    @Override
    public double popNextPressurePsiValue() {
        return mockNextPressurePsiValue;
    }

    public void mockPopNextPressurePsiValue(double mockReturnValue) {
        this.mockNextPressurePsiValue = mockReturnValue;
    }
}
