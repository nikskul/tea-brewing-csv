package ru.croc.nikskul.output;

import ru.croc.nikskul.domain.BrewingLog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class BrewingExportManager {

    public void exportBrewing(
        List<BrewingLog> brewingLogs,
        URI destination
    ) {

        List<BrewingLog> brewings = brewingLogs.stream()
            .filter(log -> {
                var goodTime = log.getTea().getTeaType().getBrewingTimeTo() - log.getTea().getTeaType().getBrewingTimeFrom();

                return ChronoUnit.SECONDS.between(
                    log.getBrewingStart(),
                    log.getBrewingEnd()
                ) <= goodTime;
            }).collect(Collectors.toList());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination.getPath()))) {

            var dates = brewingLogs.stream()
                .map(brewing -> LocalDate.from(brewing.getBrewingStart()))
                .collect(Collectors.toSet());

            dates.forEach(date -> {
                try {

                    var dateBrew = brewings.stream().filter(brew ->
                            LocalDate.from(brew.getBrewingStart()).isEqual(date))
                        .collect(Collectors.toSet());

                    String employees = dateBrew.stream()
                        .map(BrewingLog::getEmployeeName)
                        .distinct()
                        .collect(Collectors.joining(", "));

                    writer.write(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy - ")) + employees);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
