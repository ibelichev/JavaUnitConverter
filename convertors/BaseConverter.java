package convertors;
public abstract class BaseConverter <T>{

    /**
     * Переводит единицы измерения
     * @param originValue значение, которое переводим
     * @param from единцы измерения из которых переводим
     * @param to единицы измерения в которые переводим
     * @return возвращает сконвертированное значение
     */
    public abstract double convert(double originValue, T from, T to);

    /**
     * Переводим в СИ
     * @param originValue исходное значение
     * @param from из какой единицы измерения переводим
     * @return значение в СИ
     */
    protected abstract double toSI(double originValue, T from);
}