public class Length {
    private final double value;
    private final String unit;

    public Length(double val, String unit) {
        this.value = val;
        this.unit = unit;
    }

    public Length as(String u) {
        Length len = convertUnit(u, this.value);
        if (len != null) {
            return len;
        }

        return this;
    }

    public Length convertUnit(String unit, double value) {
        Length len = null;
        if (checkConversionWay(unit, Unit.UNIT_F, Unit.UNIT_YARD)) {
            len = new Length(value / 3, unit);
        }

        if (checkConversionWay(unit, Unit.UNIT_F, Unit.UNIT_INCH)) {
            len = new Length(value * 12, unit);
        }

        if (checkConversionWay(unit, Unit.UNIT_YARD, Unit.UNIT_INCH)) {
            len = new Length(value * 36, unit);
        }

        if (checkConversionWay(unit, Unit.UNIT_YARD, Unit.UNIT_F)) {
            len = new Length(value * 3, unit);
        }

        if (checkConversionWay(unit, Unit.UNIT_INCH, Unit.UNIT_F)) {
            len = new Length(value / 12, unit);
        }

        if (checkConversionWay(unit, Unit.UNIT_INCH, Unit.UNIT_YARD)) {
            len = new Length(value / 36, unit);
        }
        return len;
    }

    private boolean checkConversionWay(String u, String unitF, String unitYard) {
        return Unit.unitEquals(this.unit, unitF) && Unit.unitEquals(u, unitYard);
    }

    public double getVal() {
        return this.value;
    }

    public String getUnit() {
        return this.unit;
    }
}
