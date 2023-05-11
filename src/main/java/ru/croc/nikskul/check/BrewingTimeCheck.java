package ru.croc.nikskul.check;

import ru.croc.nikskul.domain.BrewingLog;

import java.time.temporal.ChronoUnit;

/**
 * Компонент проверки заваривания на соответствие времени заваривания чая.
 */
public class BrewingTimeCheck implements BrewingCheck {
    @Override
    public boolean checkIsValid(BrewingLog brewingLog) {
        var startBrewingAt = brewingLog.getBrewingStart();
        var endBrewingAt = brewingLog.getBrewingEnd();

        var brewingTime = ChronoUnit.SECONDS.between(startBrewingAt, endBrewingAt);

        var minimumBrewingTime = brewingLog.getTea().getTeaType().getBrewingTimeFrom();
        var maximumBrewingTime = brewingLog.getTea().getTeaType().getBrewingTimeTo();

        return brewingTime >= minimumBrewingTime && brewingTime <= maximumBrewingTime;
    }
}
