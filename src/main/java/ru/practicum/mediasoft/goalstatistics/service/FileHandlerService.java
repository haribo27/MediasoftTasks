package ru.practicum.mediasoft.goalstatistics.service;

import ru.practicum.mediasoft.goalstatistics.util.ClassManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandlerService {

    private final static String INPUT_FILE = "goalscorers.csv";
    private final static String OUTPUT_FILE = "result.json";
    private final TextHandlerService textHandlerService = ClassManager.getTextHandlerService();

    public void readFile() {
        String inputText = "";
        try {
            inputText = new String(Files.readAllBytes(Path.of(INPUT_FILE)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        textHandlerService.handleText(inputText);
    }

    public void writeFile(String json) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            writer.write(json);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
