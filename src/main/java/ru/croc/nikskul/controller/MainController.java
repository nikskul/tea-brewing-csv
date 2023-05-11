package ru.croc.nikskul.controller;

import ru.croc.nikskul.domain.BrewingLog;
import ru.croc.nikskul.domain.Tea;
import ru.croc.nikskul.input.BrewingLogImportManager;
import ru.croc.nikskul.input.TeaImportManager;
import ru.croc.nikskul.output.BrewingExportManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * Основной API приложения.
 */
public class MainController {

    private final TeaImportManager teaImportManager;
    private final BrewingLogImportManager brewingLogImportManager;

    private final BrewingExportManager brewingExportManager;

    public MainController(
        TeaImportManager teaImportManager,
        BrewingLogImportManager brewingLogImportManager,
        BrewingExportManager brewingExportManager
    ) {
        this.teaImportManager = teaImportManager;
        this.brewingLogImportManager = brewingLogImportManager;
        this.brewingExportManager = brewingExportManager;
    }

    /**
     * Метод для импорта чая.
     *
     * @param teaStorage путь до файла с данными по чаю.
     * @param teaTypeStorage путь до файла с данными по типам чая
     * @return Словарь ({@link Integer} ид чая - {@link Tea} чай).
     * @throws IOException исключение при чтении из файлов.
     */
    public Map<Integer, Tea> importTea(Path teaStorage, Path teaTypeStorage) throws IOException {
        return teaImportManager.importTea(teaStorage, teaTypeStorage);
    }
    /**
     * Метод для импорта записей завариваний чая.
     *
     * @param teas Словарь ({@link Integer} ид чая - {@link Tea} чай)
     * @param storage путь до файла с записями завариваний чая
     * @return список записей о заваривании чая
     * @throws IOException исключение при чтении из файла
     */
    public List<BrewingLog> importBrewingLogs(
        Map<Integer, Tea> teas,
        Path storage
    ) throws IOException {
        return brewingLogImportManager.importBrewingLog(teas, storage);
    }

    /**
     * Метод для экспорта сотрудников без нарушений завариваний.
     *
     * @param brewingLogs список записей о заваривании чая
     * @param destination путь до файла экспорта
     * @throws IOException исключение при записи в файл
     */
    public void export(List<BrewingLog> brewingLogs, Path destination) throws IOException {
        brewingExportManager.exportBrewing(brewingLogs, destination);
    }

}
