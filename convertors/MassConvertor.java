package convertors;

import units.MassEnum;

/**
 * Конвертор массы
 *
 * поведение описано в базовом классе
 */
public class MassConvertor extends BaseConverter<MassEnum> {
    @Override
    public double convert(double originValue, MassEnum from, MassEnum to) {
        if (from.equals(to)) return originValue;

        double convertedToSI = toSI(originValue, from);

        // переводим из килограммов
        return switch (to) {
            case KILOGRAM -> convertedToSI;
            case POUND -> convertedToSI / 0.45359237;
            case OUNCE -> convertedToSI / 0.02834952;
        };
    }

    // в килограммы
    @Override
    protected double toSI(double originValue, MassEnum from) {
        return switch (from) {
            case KILOGRAM -> originValue;
            case POUND -> originValue * 0.45359237;
            case OUNCE -> originValue * 0.02834952;
        };
    }
}