package ru.croc.nikskul.input;

import ru.croc.nikskul.domain.Tea;
import ru.croc.nikskul.domain.TeaType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class TeaImportManager {

    private final TeaTypeImportManager teaTypeImportManager;

    public TeaImportManager(TeaTypeImportManager teaTypeImportManager) {
        this.teaTypeImportManager = teaTypeImportManager;
    }

    public List<Tea> importTea(URI teaUri, URI typesUri) {

        List<TeaType> teaTypes = new ArrayList<>(teaTypeImportManager.importTeaTypes(typesUri));

        List<Tea> teas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(teaUri.getPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(";");

                Tea tea = new Tea();
                tea.setId(Integer.valueOf(info[0]));
                tea.setName(info[1]);
                tea.setTeaType(teaTypes.stream()
                    .filter(type -> type.getId().equals(Integer.valueOf(info[2])))
                    .findFirst()
                    .get()
                );

                teas.add(tea);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return teas;
    }
}
