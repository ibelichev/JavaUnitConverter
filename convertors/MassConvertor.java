package convertors;

import exceptions.ConvertionException;
import units.MassEnum;

/**
 * Конвертор массы
 *
 * поведение описано в базовом классе
 */
public class MassConvertor extends BaseConverter<MassEnum> {

    private static final double ounceCf = 0.02834952;
    private static final double poundCf = 0.45359237;

    @Override
    public double convert(double originValue, MassEnum from, MassEnum to) throws ConvertionException {
        if (originValue < 0) throw new ConvertionException("число не может быть меньше нуля");

        if (from.equals(to)) return originValue;

        double convertedToSI = toSI(originValue, from);

        // переводим из килограммов
        return switch (to) {
            case KILOGRAM -> convertedToSI;
            case POUND -> convertedToSI / poundCf;
            case OUNCE -> convertedToSI / ounceCf;
        };
    }

    // в килограммы
    @Override
    protected double toSI(double originValue, MassEnum from) {
        return switch (from) {
            case KILOGRAM -> originValue;
            case POUND -> originValue * poundCf;
            case OUNCE -> originValue * ounceCf;
        };
    }
}