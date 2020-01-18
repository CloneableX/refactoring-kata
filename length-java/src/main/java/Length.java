public class Length {
    public static final String UNIT_YARD = "yard";
    public static final String UNIT_INCH = "inch";
    public static final String UNIT_F = "f";
    private final double value;
    private final String unit;

    public Length(double val, String unit) {
        this.value = val;
        this.unit = unit;
    }

    public Length as(String u) {
        Length len = this;
        if (checkConversionWay(u, UNIT_F, UNIT_YARD)) {
            len = new Length(this.value / 3, u);
        }

        if (checkConversionWay(u, UNIT_F, UNIT_INCH)) {
            len = new Length(this.value * 12, u);
        }

        if (checkConversionWay(u, UNIT_YARD, UNIT_INCH)) {
            len = new Length(this.value * 36, u);
        }

        if (checkConversionWay(u, UNIT_YARD, UNIT_F)) {
            len = new Length(this.value * 3, u);
        }

        if (checkConversionWay(u, UNIT_INCH, UNIT_F)) {
            len = new Length(this.value / 12, u);
        }

        if (checkConversionWay(u, UNIT_INCH, UNIT_YARD)) {
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

    public String getUinnt() {
        return this.unit;
    }
}
