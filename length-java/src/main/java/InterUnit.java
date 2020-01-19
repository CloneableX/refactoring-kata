public class InterUnit {
    private Unit unitEnum;

    public InterUnit(Unit unitEnum) {
        this.unitEnum = unitEnum;
    }

    public Double convert(Unit tarUnit, double value) {
        double ratio = tarUnit.ratio;
        if (ratio == 0d) {
            return value;
        }
        return (value * unitEnum.ratio) / ratio;
    }
}
