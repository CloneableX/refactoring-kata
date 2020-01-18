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
        if (checkUnitType(this.unit, UNIT_F) && checkUnitType(u, UNIT_YARD)) {
            len = new Length(this.value / 3, u);
        }

        if (checkUnitType(this.unit, UNIT_F) && checkUnitType(u, UNIT_INCH)) {
            len = new Length(this.value * 12, u);
        }

        if (checkUnitType(this.unit, UNIT_YARD) && checkUnitType(u, UNIT_INCH)) {
            len = new Length(this.value * 36, u);
        }

        if (checkUnitType(this.unit, UNIT_YARD) && checkUnitType(u, UNIT_F)) {
            len = new Length(this.value * 3, u);
        }

        if (checkUnitType(this.unit, UNIT_INCH) && checkUnitType(u, UNIT_F)) {
            len = new Length(this.value / 12, u);
        }

        if (checkUnitType(this.unit, UNIT_INCH) && checkUnitType(u, UNIT_YARD)) {
            len = new Length(this.value / 36, u);
        }

        return len;
    }

    private boolean checkUnitType(String srcUnit, String unitType) {
        return srcUnit.equals(unitType);
    }

    public double getVal() {
        return this.value;
    }

    public String getUinnt() {
        return this.unit;
    }
}
