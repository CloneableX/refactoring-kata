public class InterUnit {
    private Unit unit;

    public InterUnit(Unit unit) {
        this.unit = unit;
    }

    public Double convert(UnitEnum tarUnit, double value) {
        double ratio = tarUnit.ratio;
        if (ratio == 0d) {
            return value;
        }
        return (value * unit.getRatio()) / ratio;
    }
}
