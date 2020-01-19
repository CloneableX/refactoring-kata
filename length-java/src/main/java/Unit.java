public enum Unit {
    YARD(36d), Inch(1d), Foot(12d);
    public final double ratio;

    Unit(double ratio) {
        this.ratio = ratio;
    }

    public Double convert(Unit tarUnit, double value) {
        return (value * this.ratio) / tarUnit.ratio;
    }
}
