package ru.croc.nikskul.input;

import ru.croc.nikskul.domain.Tea;
import ru.croc.nikskul.domain.TeaType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class TeaTypeImportManager {

    public List<TeaType> importTeaTypes(URI uri) {
        List<TeaType> teaTypes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(uri.getPath()))) {
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

                teaTypes.add(teaType);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return teaTypes;
    }
}
