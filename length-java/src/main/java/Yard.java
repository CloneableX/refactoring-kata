public class Yard extends Unit {
    public Yard() {
        super(36);
    }

    @Override
    public Double convert(String tarUnit, double value) {
        if (unitEquals(tarUnit, UNIT_FOOT)) {
            return value * 3;
        }

        if (unitEquals(tarUnit, UNIT_INCH)) {
            return value * 36;
        }

        return value;
    }
}
