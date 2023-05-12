package ru.croc.nikskul;

import ru.croc.nikskul.controller.MainController;
import ru.croc.nikskul.domain.BrewingLog;
import ru.croc.nikskul.domain.Tea;
import ru.croc.nikskul.input.BrewingLogImportManager;
import ru.croc.nikskul.input.TeaImportManager;
import ru.croc.nikskul.input.TeaTypeImportManager;
import ru.croc.nikskul.output.BrewingExportManager;
import ru.croc.nikskul.service.BrewingGroupByDateUseCase;
import ru.croc.nikskul.service.BrewingValidationService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * Точка входа в приложение.
 */
public class App {
    public static void main(String[] args) {
        var controller = new MainController(
            new TeaImportManager(
                new TeaTypeImportManager()
            ),
            new BrewingLogImportManager(),
            new BrewingExportManager(
                new BrewingValidationService(),
                new BrewingGroupByDateUseCase()
            )
        );

        var currentPath = Path.of("").toAbsolutePath();

        Map<Integer, Tea> teas;
        try {
            teas = controller.importTea(
                currentPath.resolve("data/tea.csv"),
                currentPath.resolve("data/tea_type.csv")
            );
        } catch (IOException e) {
            throw new RuntimeException("Не удалось импортировать чай. " + e.getMessage());
        }

        List<BrewingLog> brewing;
        try {
            brewing = controller.importBrewingLogs(
                teas,
                currentPath.resolve("data/tea_brewing.csv")
            );
        } catch (IOException e) {
            throw new RuntimeException(
                "Не удалось импортировать записи о заваривании чая. "
                    + e.getMessage()
            );
        }

        try {
            controller.export(
                brewing,
                currentPath.resolve("result.txt")
            );
        } catch (IOException e) {
            throw new RuntimeException(
                "Не удалось экспортировать записи о сотрудниках. "
                    + e.getMessage()
            );
        }
    }
}
