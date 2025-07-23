package convertors;

import exceptions.ConvertionException;
import units.LengthEnum;

/**
 * Конвертор длины
 * поведение описано в базовом классе
 */
public class LengthConvertor extends BaseConverter<LengthEnum> {

    private static final int kilometerCf = 1000;
    private static final int mileCf = 1609;

    @Override
    public double convert(double originValue, LengthEnum from, LengthEnum to) throws ConvertionException {
        if (originValue < 0) throw new ConvertionException("число не может быть меньше нуля");

        if (from.equals(to)) return originValue;

        double convertedToSI = toSI(originValue, from);

        return switch (to) {
            case METER -> convertedToSI;
            case MILE -> convertedToSI * mileCf;
            case KILOMETER -> convertedToSI / kilometerCf;
        };
    }

    // переводим в метры
    @Override
    protected double toSI(double originValue, LengthEnum from) {
        return switch (from) {
            case METER -> originValue;
            case KILOMETER -> originValue * kilometerCf;
            case MILE -> originValue * mileCf;
        };
    }
}