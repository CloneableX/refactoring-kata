public class Length {
    private final double value;
    private final String unit;

    public Length(double val, String unit) {
        this.value = val;
        this.unit = unit;
    }

    public Length as(String u) {
        Length len = Unit.create(this.unit).convert(u, value);
        if (len != null) {
            return len;
        }

        return this;
    }

    public double getVal() {
        return this.value;
    }

    public String getUnit() {
        return this.unit;
    }
}
