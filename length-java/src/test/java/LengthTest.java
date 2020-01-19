import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LengthTest {
    @Test
    public void should_1_inch_equals_1_inch() {
        Length result = new Length(1, Unit.Inch).as(Unit.Inch);

        assertThat(result.getVal(), is(1.0));
        assertThat(result.getUnit(), is(Unit.Inch));
    }

    @Test
    public void should_2_feet_equals_2_feet() {
        Length result = new Length(2, Unit.Foot).as(Unit.Foot);

        assertThat(result.getVal(), is(2.0));
        assertThat(result.getUnit(), is(Unit.Foot));
    }

    @Test
    public void should_1_yard_equals_1_yard() {
        Length result = new Length(1, Unit.YARD).as(Unit.YARD);

        assertThat(result.getVal(), is(1.0));
        assertThat(result.getUnit(), is(Unit.YARD));
    }

    @Test
    public void should_1_foot_equals_12_inches() {
        Length result = new Length(1, Unit.Foot).as(Unit.Inch);

        assertThat(result.getVal(), is(12.0));
        assertThat(result.getUnit(), is(Unit.Inch));
    }

    @Test
    public void should_3_foot_equals_1_yard() {
        Length result = new Length(3, Unit.Foot).as(Unit.YARD);

        assertThat(result.getVal(), is(1.0));
        assertThat(result.getUnit(), is(Unit.YARD));
    }

    @Test
    public void should_1_yard_equals_3_feet() {
        Length result = new Length(1, Unit.YARD).as(Unit.Foot);

        assertThat(result.getVal(), is(3.0));
        assertThat(result.getUnit(), is(Unit.Foot));
    }

    @Test
    public void should_1_yard_equals_36_inches() {
        Length result = new Length(1, Unit.YARD).as(Unit.Inch);

        assertThat(result.getVal(), is(36.0));
        assertThat(result.getUnit(), is(Unit.Inch));
    }

    @Test
    public void should_2_yards_equals_72_inches() {
        Length result = new Length(2, Unit.YARD).as(Unit.Inch);

        assertThat(result.getVal(), is(72.0));
        assertThat(result.getUnit(), is(Unit.Inch));
    }

    @Test
    public void should_12_inches_equals_1_foot() {
        Length result = new Length(12, Unit.Inch).as(Unit.Foot);

        assertThat(result.getVal(), is(1.0));
        assertThat(result.getUnit(), is(Unit.Foot));
    }

    @Test
    public void should_36_inches_equals_1_yard() {
        Length result = new Length(36, Unit.Inch).as(Unit.YARD);

        assertThat(result.getVal(), is(1.0));
        assertThat(result.getUnit(), is(Unit.YARD));
    }

    @Test
    public void should_18_inches_equals_half_yard() {
        Length result = new Length(18, Unit.Inch).as(Unit.YARD);

        assertThat(result.getVal(), is(0.5));
        assertThat(result.getUnit(), is(Unit.YARD));
    }
}
