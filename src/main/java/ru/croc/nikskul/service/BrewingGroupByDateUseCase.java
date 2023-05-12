package ru.croc.nikskul.service;

import ru.croc.nikskul.domain.BrewingLog;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class BrewingGroupByDateUseCase {

    public Map<LocalDate, Map<String, List<BrewingLog>>> getMappedByDateEmployeeNamesHaveOnlyValidBrewing(
            List<BrewingLog> brewingLogs
    ) {
        // Группировка завариваний по датам и сотрудникам.
        return brewingLogs.stream()
            .collect(
                groupingBy(
                    brew -> LocalDate.from(brew.getBrewingStart()),
                    groupingBy(BrewingLog::getEmployeeName)
                )
            );
    }
}
