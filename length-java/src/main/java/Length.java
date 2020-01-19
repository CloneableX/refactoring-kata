public class Length {
    private final double value;
    private Unit unitEnum;

    public Length(double val, Unit unitEnum) {
        this.value = val;
        this.unitEnum = unitEnum;
    }

    public Length as(Unit tarUnit) {
        InterUnit interUnit = new InterUnit(unitEnum);
        Double convertVal = interUnit.convert(tarUnit, value);
        return new Length(convertVal, tarUnit);
    }

    public double getVal() {
        return this.value;
    }

    public Unit getUnit() {
        return this.unitEnum;
    }
}
