public enum  UnitEnum {
    YARD(Unit.UNIT_YARD, 36), Inch(Unit.UNIT_INCH, 1), Foot(Unit.UNIT_FOOT, 12);
    public final String type;
    public final double ratio;

    UnitEnum(String type, double ratio) {
        this.type = type;
        this.ratio = ratio;
    }
}
