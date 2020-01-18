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
}
