public class Unit {
    public static final String UNIT_YARD = "yard";
    public static final String UNIT_INCH = "inch";
    public static final String UNIT_F = "f";
    private String type;

    public Unit(String type) {
        this.type = type;
    }

    static boolean unitEquals(String srcUnit, String tarUnit) {
        return srcUnit.equals(tarUnit);
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

        if (checkConversionWay(UNIT_YARD, tarUnit, UNIT_INCH)) {
            return new Length(value * 36, tarUnit);
        }

        if (checkConversionWay(UNIT_YARD, tarUnit, UNIT_F)) {
            return new Length(value * 3, tarUnit);
        }

        if (checkConversionWay(UNIT_INCH, tarUnit, UNIT_F)) {
            return new Length(value / 12, tarUnit);
        }

        if (checkConversionWay(UNIT_INCH, tarUnit, UNIT_YARD)) {
            return new Length(value / 36, tarUnit);
        }

        return null;
    }

    public String getType() {
        return type;
    }
}
