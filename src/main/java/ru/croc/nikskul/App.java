package ru.croc.nikskul;

import ru.croc.nikskul.controller.MainController;
import ru.croc.nikskul.input.BrewingLogImportManager;
import ru.croc.nikskul.input.TeaImportManager;
import ru.croc.nikskul.input.TeaTypeImportManager;
import ru.croc.nikskul.output.BrewingExportManager;

import java.nio.file.Path;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        var controller = new MainController(
            new TeaImportManager(
                new TeaTypeImportManager()
            ),
            new BrewingLogImportManager(),
            new BrewingExportManager()
        );

        var currentPath = Path.of("").toAbsolutePath();
        var teas = controller.importTea(
            currentPath.resolve("data/tea.csv").toUri(),
            currentPath.resolve("data/tea_type.csv").toUri()
        );

        var brewing = controller.importBrewingLog(
            teas,
            currentPath.resolve("data/tea_brewing.csv").toUri()
        );

        controller.export(
            brewing,
            currentPath.resolve("result.txt").toUri()
        );
    }
}
