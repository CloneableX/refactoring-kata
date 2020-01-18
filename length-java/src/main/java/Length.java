public class Length {
    public static final String YARD = "yard";
    public static final String INCH = "inch";
    public static final String F = "f";
    private final double value;
    private final String unit;

    public Length(double val, String uinnt) {
        this.value = val;
        this.unit = uinnt;
    }

    public Length as(String u) {
        Length len = this;
        if (this.unit.equals(F)) {
            if (u.equals(YARD)) {
                len = new Length(this.value / 3, u);
            } else if (u.equals(INCH)) {
                len = new Length(this.value * 12, u);
            }
        }

        if (this.unit.equals(YARD)) {
            if (u.equals(INCH)) {
                len = new Length(this.value * 36, u);
            } else if (u.equals(F)){
                len = new Length(this.value * 3, u);
            }
        }

        if (this.unit.equals(INCH)) {
            if (u.equals(F)) {
                len = new Length(this.value / 12, u);
            } else if (u.equals(YARD)) {
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
