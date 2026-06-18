package fr.aku.rovermission.infrastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputFileParser {

    public List<String> readLines(Path inputFile) throws IOException {
        return Files.readAllLines(inputFile).stream()
            .map(String::trim)
            .filter(line -> !line.isEmpty())
            .toList();
    }
}
