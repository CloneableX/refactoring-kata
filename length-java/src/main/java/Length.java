public class Length {
    private final double value;
    private final String unit;

    public Length(double val, String unit) {
        this.value = val;
        this.unit = unit;
    }

    public Length as(UnitEnum tarUnit) {
        Double convertVal = new InterUnit(Unit.create(this.unit)).convert(tarUnit, value);
        return new Length(convertVal, tarUnit.type);
    }

    public Length as(String u) {
        if (Unit.UNIT_YARD.equals(u))
            return as(UnitEnum.YARD);

        if (Unit.UNIT_INCH.equals(u))
            return as(UnitEnum.Inch);

        return as(UnitEnum.Foot);
    }

    public double getVal() {
        return this.value;
    }

    public String getUnit() {
        return this.unit;
    }
}
