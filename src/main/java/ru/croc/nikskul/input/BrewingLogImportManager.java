package ru.croc.nikskul.input;

import ru.croc.nikskul.domain.BrewingLog;
import ru.croc.nikskul.domain.Tea;
import ru.croc.nikskul.domain.TeaType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BrewingLogImportManager {

    public List<BrewingLog> importBrewingLog(List<Tea> teas, URI uri) {

        List<BrewingLog> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(uri.getPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(";");

                BrewingLog log = new BrewingLog();
                log.setEmployeeName(info[0]);
                log.setTea(teas.stream()
                    .filter(tea -> tea.getId().equals(Integer.valueOf(info[1])))
                    .findFirst()
                    .get()
                );
                log.setBrewingStart(LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(info[2])));
                log.setBrewingEnd(LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(info[3])));
                log.setBrewingTemp(Integer.valueOf(info[4]));

                logs.add(log);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return logs;
    }
}
