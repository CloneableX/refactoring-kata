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

    static boolean checkConversionWay(String srcUnit, String srcUnitType, String tarUnit, String tarUnitType) {
        return unitEquals(srcUnit, srcUnitType) && unitEquals(tarUnit, tarUnitType);
    }

    public static Length convert(Length length, String tarUnit) {
        double value = length.getVal();
        String srcUnit = length.getUnit();
        if (checkConversionWay(srcUnit, UNIT_F, tarUnit, UNIT_YARD)) {
            return new Length(value / 3, tarUnit);
        }

        if (checkConversionWay(srcUnit, UNIT_F, tarUnit, UNIT_INCH)) {
            return new Length(value * 12, tarUnit);
        }

        if (checkConversionWay(srcUnit, UNIT_YARD, tarUnit, UNIT_INCH)) {
            return new Length(value * 36, tarUnit);
        }

        if (checkConversionWay(srcUnit, UNIT_YARD, tarUnit, UNIT_F)) {
            return new Length(value * 3, tarUnit);
        }

        if (checkConversionWay(srcUnit, UNIT_INCH, tarUnit, UNIT_F)) {
            return new Length(value / 12, tarUnit);
        }

        if (checkConversionWay(srcUnit, UNIT_INCH, tarUnit, UNIT_YARD)) {
            return new Length(value / 36, tarUnit);
        }

        return null;
    }

    public String getType() {
        return type;
    }
}
