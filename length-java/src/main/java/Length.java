public class Length {
    private final double value;
    private Unit unit;

    public Length(double val, Unit unitEnum) {
        this.value = val;
        this.unit = unitEnum;
    }

    public Length as(Unit tarUnit) {
        Double convertVal = unit.convert(tarUnit, value);
        return new Length(convertVal, tarUnit);
    }

    public double getVal() {
        return this.value;
    }

    public Unit getUnit() {
        return this.unit;
    }
}
