package ru.croc.nikskul.output;

import ru.croc.nikskul.domain.BrewingLog;
import ru.croc.nikskul.service.BrewingGroupByDateUseCase;
import ru.croc.nikskul.service.BrewingValidationService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class BrewingExportManager {

    private final BrewingValidationService brewingValidationService;
    private final BrewingGroupByDateUseCase brewingGroupByDateUseCase;

    public BrewingExportManager(
        BrewingValidationService brewingValidationService,
        BrewingGroupByDateUseCase brewingGroupByDateUseCase
    ) {
        this.brewingValidationService = brewingValidationService;
        this.brewingGroupByDateUseCase = brewingGroupByDateUseCase;
    }

    public void exportBrewing(
        List<BrewingLog> brewingLogs,
        Path destination
    ) throws IOException {

        // Группировка завариваний по датам и сотрудникам.
        var employeeNamesDateMap = brewingGroupByDateUseCase
            .getMappedByDateEmployeeNamesHaveOnlyValidBrewing(
                brewingLogs
            );

        // Удаляем все вхождения сотрудников с неправильным завариванием.
        employeeNamesDateMap.forEach(
            (date, map) -> removeEmployeesWithNotValidBrewing(map)
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination.toFile()))) {
            for (var entry : employeeNamesDateMap.entrySet()) {
                var date = entry.getKey();
                var map = entry.getValue();

                String employees = String.join(", ", map.keySet());

                writer.write(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy - ")) + employees);
                writer.newLine();
            }
        }
    }

    private void removeEmployeesWithNotValidBrewing(
        Map<String, List<BrewingLog>> brewingLogsByEmployee
    ) {
        brewingLogsByEmployee.entrySet()
            .removeIf(Predicate.not(this::isAllChecksPassed));
    }

    private boolean isAllChecksPassed(
        Map.Entry<String, List<BrewingLog>> entry
    ) {
        return brewingValidationService.isAllChecksPassed(entry.getValue());
    }
}
