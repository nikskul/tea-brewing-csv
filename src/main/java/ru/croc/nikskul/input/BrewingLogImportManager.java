package ru.croc.nikskul.input;

import ru.croc.nikskul.domain.BrewingLog;
import ru.croc.nikskul.domain.Tea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrewingLogImportManager {

    public List<BrewingLog> importBrewingLog(Map<Integer, Tea> teas, Path storage) throws IOException {

        List<BrewingLog> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(storage.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(";");

                BrewingLog log = new BrewingLog();
                log.setEmployeeName(info[0]);
                log.setTea(teas.get(Integer.valueOf(info[1])));
                log.setBrewingStart(LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(info[2])));
                log.setBrewingEnd(LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(info[3])));
                log.setBrewingTemp(Integer.valueOf(info[4]));

                logs.add(log);
            }
        }

        return logs;
    }
}
