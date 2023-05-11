package ru.croc.nikskul.output;

import ru.croc.nikskul.domain.BrewingLog;
import ru.croc.nikskul.domain.Tea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class BrewingExportManager {

    public void exportBrewing(
        List<BrewingLog> brewingLogs,
        URI destination
    ) {

        var brewings = brewingLogs.stream()
            .filter(log -> {
                var goodTime = log.getTea().getTeaType().getBrewingTimeTo() - log.getTea().getTeaType().getBrewingTimeFrom();

                return ChronoUnit.SECONDS.between(
                    log.getBrewingStart(),
                    log.getBrewingEnd()
                ) <= goodTime;
            });

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination.getPath()))) {

            var dates = brewingLogs.stream()
                .map(brewing -> {
                    return LocalDate.from(brewing.getBrewingStart());
                })
                .collect(Collectors.toSet());

            dates.forEach(date -> {
                try {
                    StringBuilder builder = new StringBuilder();
                    var dateBrew = brewings.filter(brew ->
                            LocalDate.from(brew.getBrewingStart())
                                .compareTo(date) == 0)
                        .collect(Collectors.toSet());

                    String employees = dateBrew.stream()
                        .map(BrewingLog::getEmployeeName)
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
