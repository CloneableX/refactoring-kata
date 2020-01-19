public class Foot extends Unit {
    public Foot() {
        super(12);
    }

    @Override
    public Double convert(String tarUnit, double value) {
        if (unitEquals(tarUnit, UNIT_YARD)) {
            return value / 3;
        }

        if (unitEquals(tarUnit, UNIT_INCH)) {
            return value * 12;
        }

        return value;
    }
}
