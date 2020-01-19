public class Inch extends Unit {
    public Inch() {
        super(1);
    }

    @Override
    public Double convert(String tarUnit, double value) {
        if (unitEquals(tarUnit, UNIT_FOOT)) {
            return value / 12;
        }

        if (unitEquals(tarUnit, UNIT_YARD)) {
            return value / 36;
        }

        return value;
    }
}
