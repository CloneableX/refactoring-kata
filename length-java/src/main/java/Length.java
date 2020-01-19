public class Length {
    private final double value;
    private UnitEnum unitEnum;

    public Length(double val, UnitEnum unitEnum) {
        this.value = val;
        this.unitEnum = unitEnum;
    }

    public Length as(UnitEnum tarUnit) {
        Double convertVal = new InterUnit(Unit.create(unitEnum.type)).convert(tarUnit, value);
        return new Length(convertVal, tarUnit);
    }

    public double getVal() {
        return this.value;
    }

    public UnitEnum getUnit() {
        return this.unitEnum;
    }
}
