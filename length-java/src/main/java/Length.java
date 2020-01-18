public class Length {
    private final double value;
    private final String unit;

    public Length(double val, String unit) {
        this.value = val;
        this.unit = unit;
    }

    public Length as(String u) {
        Length len = this;
        if (checkConversionWay(u, Unit.UNIT_F, Unit.UNIT_YARD)) {
            len = new Length(this.value / 3, u);
        }

        if (checkConversionWay(u, Unit.UNIT_F, Unit.UNIT_INCH)) {
            len = new Length(this.value * 12, u);
        }

        if (checkConversionWay(u, Unit.UNIT_YARD, Unit.UNIT_INCH)) {
            len = new Length(this.value * 36, u);
        }

        if (checkConversionWay(u, Unit.UNIT_YARD, Unit.UNIT_F)) {
            len = new Length(this.value * 3, u);
        }

        if (checkConversionWay(u, Unit.UNIT_INCH, Unit.UNIT_F)) {
            len = new Length(this.value / 12, u);
        }

        if (checkConversionWay(u, Unit.UNIT_INCH, Unit.UNIT_YARD)) {
            len = new Length(this.value / 36, u);
        }

        return len;
    }

    private boolean checkConversionWay(String u, String unitF, String unitYard) {
        return unitEquals(this.unit, unitF) && unitEquals(u, unitYard);
    }

    private boolean unitEquals(String srcUnit, String tarUnit) {
        return srcUnit.equals(tarUnit);
    }

    public double getVal() {
        return this.value;
    }

    public String getUnit() {
        return this.unit;
    }
}
