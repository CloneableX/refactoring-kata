public class Unit {
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

        return new Unit(type);
    }

    boolean checkConversionWay(String srcUnitType, String tarUnit, String tarUnitType) {
        return unitEquals(type, srcUnitType) && unitEquals(tarUnit, tarUnitType);
    }

    public Length convert(String tarUnit, double value) {
        if (checkConversionWay(UNIT_F, tarUnit, UNIT_YARD)) {
            return new Length(value / 3, tarUnit);
        }

        if (checkConversionWay(UNIT_F, tarUnit, UNIT_INCH)) {
            return new Length(value * 12, tarUnit);
        }

        return null;
    }

    public String getType() {
        return type;
    }
}
