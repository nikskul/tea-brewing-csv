package ru.croc.nikskul.input;

import ru.croc.nikskul.domain.Tea;
import ru.croc.nikskul.domain.TeaType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class TeaImportManager {

    private final TeaTypeImportManager teaTypeImportManager;

    public TeaImportManager(TeaTypeImportManager teaTypeImportManager) {
        this.teaTypeImportManager = teaTypeImportManager;
    }

    public Map<Integer, Tea> importTea(Path teaStorage, Path teaTypeStorage) throws IOException {

        Map<Integer, TeaType> teaTypes = new HashMap<>(
            teaTypeImportManager.importTeaTypes(teaTypeStorage)
        );

        Map<Integer, Tea> teas = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(teaStorage.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(";");

                Tea tea = new Tea();
                tea.setId(Integer.valueOf(info[0]));
                tea.setName(info[1]);
                tea.setTeaType(teaTypes.get(Integer.valueOf(info[2])));

                teas.put(tea.getId(), tea);
            }
        }

        return teas;
    }
}
