public class Length {
    private final double value;
    private final String unit;

    public Length(double val, String unit) {
        this.value = val;
        this.unit = unit;
    }

    public Length as(String u) {
        Double convertVal = Unit.create(this.unit).convertTemp(u, value);

        if (convertVal == 0d) {
            Length convert = Unit.create(this.unit).convert(u, value);
            convertVal = convert.getVal();
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
