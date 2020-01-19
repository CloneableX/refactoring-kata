public class Length {
    private final double value;
    private final Unit unit;
    private final String unitType;

    public Length(double val, String unit) {
        this.value = val;
        this.unitType = unit;
        this.unit = Unit.create(unit);
    }

    public Length as(String u) {
        Length len = unit.convert(u, value);
        if (len != null) {
            return len;
        }

        return this;
    }

    public double getVal() {
        return this.value;
    }

    public String getUnit() {
        return this.unitType;
    }
}
