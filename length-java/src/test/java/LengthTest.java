import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LengthTest {
    @Test
    public void should_1_inch_equals_1_inch() {
        Length result = new Length(1, Unit.Inch).as(Unit.Inch);

        checkUnitConvertResult(result, 1.0, Unit.Inch);
    }

    @Test
    public void should_2_feet_equals_2_feet() {
        Length result = new Length(2, Unit.Foot).as(Unit.Foot);

        checkUnitConvertResult(result, 2.0, Unit.Foot);
    }

    @Test
    public void should_1_yard_equals_1_yard() {
        Length result = new Length(1, Unit.YARD).as(Unit.YARD);

        checkUnitConvertResult(result, 1.0, Unit.YARD);
    }

    @Test
    public void should_1_foot_equals_12_inches() {
        Length result = new Length(1, Unit.Foot).as(Unit.Inch);

        checkUnitConvertResult(result, 12.0, Unit.Inch);
    }

    @Test
    public void should_3_foot_equals_1_yard() {
        Length result = new Length(3, Unit.Foot).as(Unit.YARD);

        checkUnitConvertResult(result, 1.0, Unit.YARD);
    }

    @Test
    public void should_1_yard_equals_3_feet() {
        Length result = new Length(1, Unit.YARD).as(Unit.Foot);

        checkUnitConvertResult(result, 3.0, Unit.Foot);
    }

    @Test
    public void should_1_yard_equals_36_inches() {
        Length result = new Length(1, Unit.YARD).as(Unit.Inch);

        checkUnitConvertResult(result, 36.0, Unit.Inch);
    }

    @Test
    public void should_2_yards_equals_72_inches() {
        Length result = new Length(2, Unit.YARD).as(Unit.Inch);

        checkUnitConvertResult(result, 72.0, Unit.Inch);
    }

    @Test
    public void should_12_inches_equals_1_foot() {
        Length result = new Length(12, Unit.Inch).as(Unit.Foot);

        checkUnitConvertResult(result, 1.0, Unit.Foot);
    }

    @Test
    public void should_36_inches_equals_1_yard() {
        Length result = new Length(36, Unit.Inch).as(Unit.YARD);

        checkUnitConvertResult(result, 1.0, Unit.YARD);
    }

    @Test
    public void should_18_inches_equals_half_yard() {
        Length result = new Length(18, Unit.Inch).as(Unit.YARD);

        checkUnitConvertResult(result, 0.5, Unit.YARD);
    }

    private void checkUnitConvertResult(Length result, double value, Unit unit) {
        assertThat(result, is(new Length(value, unit)));
    }
}
