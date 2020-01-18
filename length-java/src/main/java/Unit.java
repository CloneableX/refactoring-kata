public class Unit {
    public static final String UNIT_YARD = "yard";
    public static final String UNIT_INCH = "inch";
    public static final String UNIT_F = "f";

    static boolean unitEquals(String srcUnit, String tarUnit) {
        return srcUnit.equals(tarUnit);
    }

    static boolean checkConversionWay(String srcUnit, String srcUnitType, String tarUnit, String tarUnitType) {
        return unitEquals(srcUnit, srcUnitType) && unitEquals(tarUnit, tarUnitType);
    }

    public static Length convertUnit(Length length, String tarUnit) {
        Length len = null;
        double value = length.getVal();
        String srcUnit = length.getUnit();
        if (checkConversionWay(srcUnit, UNIT_F, tarUnit, UNIT_YARD)) {
            len = new Length(value / 3, tarUnit);
        }

        if (checkConversionWay(srcUnit, UNIT_F, tarUnit, UNIT_INCH)) {
            len = new Length(value * 12, tarUnit);
        }

        if (checkConversionWay(srcUnit, UNIT_YARD, tarUnit, UNIT_INCH)) {
            len = new Length(value * 36, tarUnit);
        }

        if (checkConversionWay(srcUnit, UNIT_YARD, tarUnit, UNIT_F)) {
            len = new Length(value * 3, tarUnit);
        }

        if (checkConversionWay(srcUnit, UNIT_INCH, tarUnit, UNIT_F)) {
            len = new Length(value / 12, tarUnit);
        }

        if (checkConversionWay(srcUnit, UNIT_INCH, tarUnit, UNIT_YARD)) {
            len = new Length(value / 36, tarUnit);
        }
        return len;
    }
}
