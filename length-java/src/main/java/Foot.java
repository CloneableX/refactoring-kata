public class Foot extends Unit {
    public Foot() {
        super();
    }

    @Override
    public Length convert(String tarUnit, double value) {
        if (unitEquals(tarUnit, UNIT_YARD)) {
            return new Length(value / 3, tarUnit);
        }

        if (unitEquals(tarUnit, UNIT_INCH)) {
            return new Length(value * 12, tarUnit);
        }

        return null;
    }
}
