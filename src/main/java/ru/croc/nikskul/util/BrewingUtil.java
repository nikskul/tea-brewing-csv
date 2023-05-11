package ru.croc.nikskul.util;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import ru.croc.nikskul.domain.BrewingLog;
import ru.croc.nikskul.service.BrewingValidationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Утилитарный класс для работы с записями о заваривании.
 */
public final class BrewingUtil {

    private BrewingUtil() {
    }

    public static Map<LocalDate, List<String>> getMappedByDateEmployeeNamesHaveOnlyValidBrewing(
        BrewingValidationService brewingValidationService,
        List<BrewingLog> brewingLogs
    ) {
        Map<LocalDate, List<BrewingLog>> brewingByDateMap = groupByDate(brewingLogs);

        var result = new HashMap<LocalDate, List<String>>();
        brewingByDateMap.forEach((key, value) -> {
            Map<String, List<BrewingLog>> brewingLogsByEmployee = value.stream()
                .collect(groupingBy(BrewingLog::getEmployeeName, toList()));

            removeEmployeesWithNotValidBrewing(brewingValidationService, brewingLogsByEmployee);

            result.put(key, new ArrayList<>(brewingLogsByEmployee.keySet()));
        });

        return result;
    }

    private static Map<LocalDate, List<BrewingLog>> groupByDate(List<BrewingLog> brewingLogs) {
        return brewingLogs.stream()
            .collect(
                groupingBy(
                    brew -> LocalDate.from(brew.getBrewingStart()),
                    toList()
                )
            );
    }

    private static void removeEmployeesWithNotValidBrewing(
        BrewingValidationService brewingValidationService,
        Map<String, List<BrewingLog>> brewingLogsByEmployee
    ) {
        brewingLogsByEmployee.entrySet()
            .removeIf(entry -> !brewingValidationService.validateIsNoWarns(entry.getValue()));
    }

}
