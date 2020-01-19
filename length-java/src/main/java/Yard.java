public class Yard extends Unit {
    public Yard() {
        super();
    }

    @Override
    public Length convert(String tarUnit, double value) {
        if (unitEquals(tarUnit, UNIT_FOOT)) {
            return new Length(value * 3, tarUnit);
        }

        if (unitEquals(tarUnit, UNIT_INCH)) {
            return new Length(value * 36, tarUnit);
        }

        return null;
    }
}
