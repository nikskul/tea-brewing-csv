package ru.croc.nikskul.service;

import ru.croc.nikskul.check.BrewingCheck;
import ru.croc.nikskul.check.BrewingTempCheck;
import ru.croc.nikskul.check.BrewingTimeCheck;
import ru.croc.nikskul.domain.BrewingLog;

import java.util.List;

/**
 * Компонент для проверки завариваний.
 */
public class BrewingValidationService {

    List<BrewingCheck> checks = List.of(
        new BrewingTimeCheck(),
        new BrewingTempCheck()
    );

    /**
     * Метод для проверки завариваний.
     *
     * @param brewingLogs список завариваний
     * @return true - если все заваривания прошли проверку.
     * false - если хотя бы одно заваривание не прошло проверку
     */
    public boolean isAllChecksPassed(List<BrewingLog> brewingLogs) {
        for (var check : checks) {
            boolean isPassed = brewingLogs.parallelStream()
                .allMatch(check::checkIsValid);
            if (!isPassed)
                return false;
        }
        return true;
    }
}
