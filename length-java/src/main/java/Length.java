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
        if (this.unit.equals(UNIT_F)) {
            if (u.equals(UNIT_YARD)) {
                len = new Length(this.value / 3, u);
            } else if (u.equals(UNIT_INCH)) {
                len = new Length(this.value * 12, u);
            }
        }

        if (this.unit.equals(UNIT_YARD)) {
            if (u.equals(UNIT_INCH)) {
                len = new Length(this.value * 36, u);
            } else if (u.equals(UNIT_F)){
                len = new Length(this.value * 3, u);
            }
        }

        if (this.unit.equals(UNIT_INCH)) {
            if (u.equals(UNIT_F)) {
                len = new Length(this.value / 12, u);
            } else if (u.equals(UNIT_YARD)) {
                len = new Length(this.value / 36, u);
            }
        }

        return len;
    }

    public double getVal() {
        return this.value;
    }

    public String getUinnt() {
        return this.unit;
    }
}
