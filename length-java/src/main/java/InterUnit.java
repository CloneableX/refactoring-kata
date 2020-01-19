public class InterUnit {
    private Unit unit;

    public InterUnit(Unit unit) {
        this.unit = unit;
    }

    public Double convert(Unit tarUnit, double value) {
        if (tarUnit.getRatio() == 0d) {
            return value;
        }
        return (value * unit.getRatio()) / tarUnit.getRatio();
    }
}
