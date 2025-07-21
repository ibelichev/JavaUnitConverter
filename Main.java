import convertors.BaseConverter;
import convertors.LengthConvertor;
import convertors.MassConvertor;
import convertors.TemperatureConvertor;
import exceptions.ConvertionException;
import exceptions.IlligalUnitException;
import exceptions.InputException;
import units.LengthEnum;
import units.MassEnum;
import units.TemperatureEnum;
import units.UnitEnum;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LengthConvertor lengthConvertor = new LengthConvertor();
        MassConvertor massConvertor = new MassConvertor();
        TemperatureConvertor temperatureConvertor = new TemperatureConvertor();

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("""
                    Введите данные в формате:
                    category value from_unit to_unit
                    """);

            String[] input = scanner.nextLine().split(" ");

            if (input[0].equals("!!")) {
                System.out.println("Выход из программы.");
                break;
            }

            try{
                if (input.length != 4) {
                    throw new InputException("Некорректный формат ввода.");
                }

                UnitEnum category = UnitEnum.valueOf(input[0].toUpperCase());

                switch (category) {
                    case LENGTH -> processConversion(lengthConvertor, input, LengthEnum.class);
                    case WEIGHT -> processConversion(massConvertor, input, MassEnum.class);
                    case TEMPERATURE -> processConversion(temperatureConvertor, input, TemperatureEnum.class);
                    default -> throw new IlligalUnitException("Неизвестная категория: " + category.name());
                }

                System.out.println("\n\n");
            } catch (InputException | IlligalUnitException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Введите корректную категорию");
            }
        }
    }

    private static <T extends Enum<T>> void processConversion(Object convertor,
                                                              String[] input,
                                                              Class<T> enumClass) {
        try {
            double value = Double.parseDouble(input[1]);
            T fromUnit = Enum.valueOf(enumClass, input[2].toUpperCase());
            T toUnit = Enum.valueOf(enumClass, input[3].toUpperCase());
            double result = ((BaseConverter<T>) convertor).convert(value, fromUnit, toUnit);
            System.out.println(value + " " + fromUnit + " = " + result + " " + toUnit);

        } catch (ConvertionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Некорректное значение конвертируемого числа");
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректные единицы измерения");
        }
    }
}