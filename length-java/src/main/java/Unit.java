public enum Unit {
    YARD("yard", 36), Inch("inch", 1), Foot("foot", 12);
    public final String type;
    public final double ratio;

    Unit(String type, double ratio) {
        this.type = type;
        this.ratio = ratio;
    }

    public Double convert(Unit tarUnit, double value) {
        return (value * this.ratio) / tarUnit.ratio;
    }
}
