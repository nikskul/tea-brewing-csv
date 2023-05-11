package ru.croc.nikskul.output;

import ru.croc.nikskul.domain.BrewingLog;
import ru.croc.nikskul.service.BrewingValidationService;
import ru.croc.nikskul.util.BrewingUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class BrewingExportManager {

    private final BrewingValidationService brewingValidationService;

    public BrewingExportManager(BrewingValidationService brewingValidationService) {
        this.brewingValidationService = brewingValidationService;
    }

    public void exportBrewing(
        List<BrewingLog> brewingLogs,
        Path destination
    ) throws IOException {

        Map<LocalDate, List<String>> employeeNamesDateMap = BrewingUtil
            .getMappedByDateEmployeeNamesHaveOnlyValidBrewing(
                brewingValidationService,
                brewingLogs
            );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination.toFile()))) {
            for (var entry : employeeNamesDateMap.entrySet()) {
                var date = entry.getKey();
                var names = entry.getValue();

                String employees = String.join(", ", names);

                if (employees.isEmpty()) {
                    continue;
                }

                writer.write(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy - ")) + employees);
                writer.newLine();
            }
        }
    }
}
