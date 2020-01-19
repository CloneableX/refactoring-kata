public class Yard extends Unit {
    public Yard() {
        super();
    }

    @Override
    public Double convertTemp(String tarUnit, double value) {
        if (unitEquals(tarUnit, UNIT_FOOT)) {
            return value * 3;
        }

        if (unitEquals(tarUnit, UNIT_INCH)) {
            return value * 36;
        }

        return value;
    }
}
