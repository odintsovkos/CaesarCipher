package ru.odinkos.di;

import ru.odinkos.bruteforce.BruteForceDecryptor;
import ru.odinkos.cipher.Cipher;
import ru.odinkos.cipher.Validator;
import ru.odinkos.filemanager.FileManager;

public class DependencyContainer {
	public final Dependencies dependencies;

	public DependencyContainer() {
		this.dependencies = new Dependencies(
				new Cipher(),
				new Validator(),
				new FileManager(),
				new BruteForceDecryptor(new Cipher())
		);
	}
}