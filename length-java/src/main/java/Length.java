public class Length {
    private final double value;
    private final String unit;

    public Length(double val, String unit) {
        this.value = val;
        this.unit = unit;
    }

    public Length as(String u) {
        Double convertVal = new InterUnit(Unit.create(this.unit)).convert(Unit.create(u), value);
        return new Length(convertVal, u);
    }

    public double getVal() {
        return this.value;
    }

    public String getUnit() {
        return this.unit;
    }
}
