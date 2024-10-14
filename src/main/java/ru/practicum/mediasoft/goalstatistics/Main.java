package ru.practicum.mediasoft.goalstatistics;

import ru.practicum.mediasoft.goalstatistics.service.FileHandlerService;
import ru.practicum.mediasoft.goalstatistics.service.OutputResultService;
import ru.practicum.mediasoft.goalstatistics.util.ClassManager;

public class Main {

    private static final FileHandlerService fileHandlerService = ClassManager.getFileHandlerService();
    private static final OutputResultService outputResultService = new OutputResultService();

    public static void main(String[] args) {
        fileHandlerService.readFile();
        String json = outputResultService.getTeamsAsJson();
        fileHandlerService.writeFile(json);
    }
}
