package tddmicroexercises.tirepressuremonitoringsystem;


import org.junit.*;
import static org.junit.Assert.*;

public class TestAlarm {

    @Test
    public void should_not_alarm_on_when_initialize_alarm() {
        Alarm alarm = new Alarm();
        assertFalse(alarm.isAlarmOn());
    }

    @Test
    public void should_alarm_on_when_less_than_low_pressure_threshold() {
        Alarm alarm = new Alarm();
        alarm.sensor = mockSensor(16d);

        alarm.check();
        assertTrue(alarm.isAlarmOn());
    }

    @Test
    public void should_alarm_on_when_when_greater_than_high_pressure_threshold() {
        Alarm alarm = new Alarm();
        alarm.sensor = mockSensor(22d);

        alarm.check();
        assertTrue(alarm.isAlarmOn());
    }

    private SensorMock mockSensor(double v) {
        SensorMock sensor = new SensorMock();
        sensor.mockPopNextPressurePsiValue(v);
        return sensor;
    }
}
