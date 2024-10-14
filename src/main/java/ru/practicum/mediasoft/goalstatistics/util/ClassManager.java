package ru.practicum.mediasoft.goalstatistics.util;

import ru.practicum.mediasoft.goalstatistics.service.FileHandlerService;
import ru.practicum.mediasoft.goalstatistics.service.MatchService;
import ru.practicum.mediasoft.goalstatistics.service.TeamService;
import ru.practicum.mediasoft.goalstatistics.service.TextHandlerService;
import ru.practicum.mediasoft.goalstatistics.storage.DataStorage;

public class ClassManager {

    private static FileHandlerService fileHandlerService;
    private static TextHandlerService textHandlerService;
    private static MatchService matchService;
    private static TeamService teamService;
    private static DataStorage dataStorage;

    private ClassManager() {}

    public static MatchService getMatchService() {
        if (matchService == null) {
            matchService = new MatchService();
        }
        return matchService;

    }

    public static FileHandlerService getFileHandlerService() {
        if (fileHandlerService == null) {
            fileHandlerService = new FileHandlerService();
        }
        return fileHandlerService;
    }

    public static TextHandlerService getTextHandlerService() {
        if (textHandlerService == null) {
            textHandlerService = new TextHandlerService();
        }
        return textHandlerService;
    }

    public static DataStorage getDataStorage() {
        if (dataStorage == null) {
            dataStorage = new DataStorage();
        }
        return dataStorage;
    }

    public static TeamService getTeamService() {
        if (teamService == null) {
            teamService = new TeamService();
        }
        return teamService;
    }
}
