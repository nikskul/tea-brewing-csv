package ru.croc.nikskul.check;

import ru.croc.nikskul.domain.BrewingLog;

/**
 * Компонент проверки заваривания на соответствие температуре.
 */
public class BrewingTempCheck implements BrewingCheck {
    @Override
    public boolean checkIsValid(BrewingLog brewingLog) {
        var brewingTemp = brewingLog.getBrewingTemp();

        var minimumTemp = brewingLog.getTea().getTeaType().getTempFrom();
        var maximumTemp = brewingLog.getTea().getTeaType().getTempTo();

        return brewingTemp >= minimumTemp && brewingTemp <= maximumTemp;
    }
}
