package tddmicroexercises.tirepressuremonitoringsystem;


import org.junit.*;
import static org.junit.Assert.*;

public class TestAlarm {

    @Test
    public void should_not_alarm_on_when_initialize_alarm() {
        Alarm alarm = new Alarm();
        assertFalse(alarm.isAlarmOn());
    }
}
