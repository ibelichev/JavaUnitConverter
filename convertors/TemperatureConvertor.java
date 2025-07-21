package convertors;

import exceptions.ConvertionException;
import units.TemperatureEnum;

public class TemperatureConvertor extends BaseConverter<TemperatureEnum> {
    @Override
    public double convert(double originValue, TemperatureEnum from, TemperatureEnum to) throws ConvertionException {
        if (originValue < 0) throw new ConvertionException("число не может быть меньше нуля");

        if (from.equals(to)) return originValue;

        double convertedToSI = toSI(originValue, from);

        // перевод из Кельвинов
        return switch (to) {
            case KELVIN -> convertedToSI;
            case CELSIUS -> convertedToSI - 273.15;
            case FAHRENHEIT -> (convertedToSI - 273.15) * 9/5 + 32;
        };
    }

    // перевод в Кельвины
    @Override
    protected double toSI(double originValue, TemperatureEnum from) {
        return switch (from) {
            case KELVIN -> originValue;
            case CELSIUS -> originValue + 273.15;
            case FAHRENHEIT -> (originValue - 32) * 5/9 + 273.15;
        };
    }
}