public class Inch extends Unit {
    public Inch() {
        super();
    }

    @Override
    public Length convert(String tarUnit, double value) {
        if (unitEquals(tarUnit, UNIT_FOOT)) {
            return new Length(value / 12, tarUnit);
        }

        if (unitEquals(tarUnit, UNIT_YARD)) {
            return new Length(value / 36, tarUnit);
        }

        return new Length(value, UNIT_INCH);
    }
}
