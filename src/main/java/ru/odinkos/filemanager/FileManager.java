package ru.odinkos.filemanager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {
	private final DateTimeFormatter formatter;

	public FileManager() {
		this.formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
	}

	public String readFile(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		return Files.readString(path, StandardCharsets.UTF_8);
	}

	public void writeFile(String fileName, String content) throws IOException {
		Path path = Paths.get(fileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
			writer.write(content);
		}
	}

	public void createDirectory(Path directory) throws IOException {
		if (!Files.exists(directory)) {
			Files.createDirectories(directory);
		}
	}

	public String generateOutputFilePath(String inputFile, String method) {
		Path inputPath = Paths.get(inputFile);
		String parentDir = inputPath.getParent().toString();
		String timestamp = LocalDateTime.now().format(formatter);
		String prefix = switch (method) {
			case "encrypted", "decrypted" -> "input";
			case "bruteforce" -> "brutforce";
			case "statistical" -> "statanalyzer";
			default -> throw new IllegalArgumentException("Неизвестный метод: " + method);
		};
		String outputFileName = String.format("%s_%s_%s.txt", prefix, method, timestamp);
		return Paths.get(parentDir, outputFileName).toString();
	}
}
