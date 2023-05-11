package ru.croc.nikskul.check;

import ru.croc.nikskul.domain.BrewingLog;

/**
 * Контракт валидации записи о заваривании чая.
 */
public interface BrewingCheck {

    /**
     * Метод проверяющий запись о заваривании чая на отсутствие нарушений.
     *
     * @param brewingLog запись о заваривании
     * @return
     * true - если проверка пройдена.
     * false - если проверка не пройдена
     */
    boolean checkIsValid(BrewingLog brewingLog);

}
