public class Unit {
    public static final String UNIT_YARD = "yard";
    public static final String UNIT_INCH = "inch";
    public static final String UNIT_F = "f";

    static boolean unitEquals(String srcUnit, String tarUnit) {
        return srcUnit.equals(tarUnit);
    }

    static boolean checkConversionWay(Length length, String u, String unitF, String unitYard) {
        return unitEquals(length.getUnit(), unitF) && unitEquals(u, unitYard);
    }

    public static Length convertUnit(Length length, String unit, double value) {
        Length len = null;
        if (checkConversionWay(length, unit, UNIT_F, UNIT_YARD)) {
            len = new Length(value / 3, unit);
        }

        if (checkConversionWay(length, unit, UNIT_F, UNIT_INCH)) {
            len = new Length(value * 12, unit);
        }

        if (checkConversionWay(length, unit, UNIT_YARD, UNIT_INCH)) {
            len = new Length(value * 36, unit);
        }

        if (checkConversionWay(length, unit, UNIT_YARD, UNIT_F)) {
            len = new Length(value * 3, unit);
        }

        if (checkConversionWay(length, unit, UNIT_INCH, UNIT_F)) {
            len = new Length(value / 12, unit);
        }

        if (checkConversionWay(length, unit, UNIT_INCH, UNIT_YARD)) {
            len = new Length(value / 36, unit);
        }
        return len;
    }
}
