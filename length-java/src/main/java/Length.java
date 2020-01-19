public class Length {
    private final double value;
    private Unit unit;

    public Length(double val, Unit unit) {
        this.value = val;
        this.unit = unit;
    }

    public Length as(Unit tarUnit) {
        return new Length(unit.convert(tarUnit, value), tarUnit);
    }

    public double getVal() {
        return this.value;
    }

    public Unit getUnit() {
        return this.unit;
    }
}
