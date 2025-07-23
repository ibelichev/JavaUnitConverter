package convertors;

import exceptions.ConvertionException;
import units.TemperatureEnum;

public class TemperatureConvertor extends BaseConverter<TemperatureEnum> {

    public static final double KelvinOffset = 273.15;
    public static final double FahrenheitCf = 9.0 / 5.0;
    public static final int FahrenheitOffset = 32;

    @Override
    public double convert(double originValue, TemperatureEnum from, TemperatureEnum to) throws ConvertionException {
        if (originValue < 0) throw new ConvertionException("число не может быть меньше нуля");

        if (from.equals(to)) return originValue;

        double convertedToSI = toSI(originValue, from);

        // перевод из Кельвинов
        return switch (to) {
            case KELVIN -> convertedToSI;
            case CELSIUS -> convertedToSI - KelvinOffset;
            case FAHRENHEIT -> (convertedToSI - KelvinOffset) * FahrenheitCf + FahrenheitOffset;
        };
    }

    // перевод в Кельвины
    @Override
    protected double toSI(double originValue, TemperatureEnum from) {
        return switch (from) {
            case KELVIN -> originValue;
            case CELSIUS -> originValue + KelvinOffset;
            case FAHRENHEIT -> (originValue - FahrenheitOffset) * FahrenheitCf + KelvinOffset;
        };
    }
}