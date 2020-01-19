public abstract class Unit {
    public static final String UNIT_INCH = "inch";
    public static final String UNIT_FOOT = "f";
    public static final String UNIT_YARD = "yard";
    private double ratio;

    protected Unit(double ratio) {
        this.ratio = ratio;
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

    public Double convert(Unit unit, double value) {
        return new InterUnit(this).convert(unit, value);
    }

    public double getRatio() {
        return this.ratio;
    }
}
