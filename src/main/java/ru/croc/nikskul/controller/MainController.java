package ru.croc.nikskul.controller;

import ru.croc.nikskul.domain.BrewingLog;
import ru.croc.nikskul.domain.Tea;
import ru.croc.nikskul.input.BrewingLogImportManager;
import ru.croc.nikskul.input.TeaImportManager;
import ru.croc.nikskul.output.BrewingExportManager;

import java.net.URI;
import java.util.List;

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


    public List<Tea> importTea(URI teaUri, URI typesUri) {
        return teaImportManager.importTea(teaUri, typesUri);
    }

    public List<BrewingLog> importBrewingLog(List<Tea> teas, URI uri) {
        return brewingLogImportManager.importBrewingLog(teas, uri);
    }

    public void export(List<BrewingLog> brewingLogs, URI destination) {
        brewingExportManager.exportBrewing(brewingLogs, destination);
    }

}
