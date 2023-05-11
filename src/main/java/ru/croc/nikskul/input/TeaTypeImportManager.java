package ru.croc.nikskul.input;

import ru.croc.nikskul.domain.TeaType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class TeaTypeImportManager {

    public Map<Integer, TeaType> importTeaTypes(Path teaTypeStorage) throws IOException {
        Map<Integer, TeaType> teaTypes = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(teaTypeStorage.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(";");

                TeaType teaType = new TeaType();
                teaType.setId(Integer.valueOf(info[0]));
                teaType.setName(info[1]);
                teaType.setBrewingTimeFrom(Integer.valueOf(info[2]));
                teaType.setBrewingTimeTo(Integer.valueOf(info[3]));
                teaType.setTempFrom(Integer.valueOf(info[4]));
                teaType.setTempTo(Integer.valueOf(info[5]));

                teaTypes.put(teaType.getId(), teaType);
            }
        }

        return teaTypes;
    }
}
