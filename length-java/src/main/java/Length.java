public class Length {
    private final double value;
    private final String unit;

    public Length(double val, String unit) {
        this.value = val;
        this.unit = unit;
    }

    public Length as(String u) {
        Unit unit = Unit.create(this.unit);
        Double convertVal = unit.convert(Unit.create(u), value);
        if (convertVal == 0) {
            convertVal = unit.convert(u, value);
        }
        return new Length(convertVal, u);
    }

    public double getVal() {
        return this.value;
    }

    public String getUnit() {
        return this.unit;
    }
}
