public abstract class Unit {
    public static final String UNIT_INCH = "inch";
    public static final String UNIT_F = "f";
    public static final String UNIT_YARD = "yard";
    private String type;

    public Unit(String type) {
        this.type = type;
    }

    static boolean unitEquals(String srcUnit, String tarUnit) {
        return srcUnit.equals(tarUnit);
    }

    public static Unit create(String type) {
        if (UNIT_YARD.equals(type))
            return new Yard();

        if (UNIT_INCH.equals(type))
            return new Inch();

        return new Foot();
    }

    public abstract Length convert(String tarUnit, double value);

    public String getType() {
        return type;
    }
}
