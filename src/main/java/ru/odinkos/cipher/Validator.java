package ru.odinkos.cipher;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Validator {
	public boolean isKeyValid(int key) {
		return key < 0 || key >= Alphabet.getLength();
	}

	public boolean isFileExists(String filePath) {
		return Files.exists(Paths.get(filePath));
	}

	public boolean isFileReadable(String filePath) {
		return Files.isReadable(Paths.get(filePath));
	}
}
