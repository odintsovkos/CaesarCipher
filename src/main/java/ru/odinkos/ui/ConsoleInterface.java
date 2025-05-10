package ru.odinkos.ui;

import ru.odinkos.cipher.Alphabet;
import ru.odinkos.di.Dependencies;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ConsoleInterface {
	private final Dependencies dependencies;
	private final Scanner scanner;

	public ConsoleInterface(Dependencies dependencies) {
		this.dependencies = dependencies;
		this.scanner = new Scanner(System.in);
	}

	public void run() {
		while (true) {
			printMenu();
			String choice = scanner.nextLine();
			try {
				switch (choice) {
					case "1":
						handleProcess("encrypted");
						break;
					case "2":
						handleProcess("decrypted");
						break;
					case "3":
						handleProcess("bruteforce");
						break;
					case "4":
//						handleStatisticalAnalysis();
						break;
					case "0":
						System.out.println("Программа завершена.");
						return;
					default:
						System.out.println("Неверный выбор. Попробуйте снова.");
				}
			} catch (IOException e) {
				System.out.println("Неизвестная ошибка: " + e.getMessage());
			} catch (NumberFormatException e) {
				System.out.println("Ошибка: Ключ должен быть целым числом.");
			}
		}
	}

	public void handleProcess(String method) throws IOException {
		System.out.print("Введите путь к файлу: ");
		String inputFile = scanner.nextLine();

		if (!dependencies.validator().isFileExists(inputFile) || !dependencies.validator().isFileReadable(inputFile)) {
			throw new IOException("Файл не существует или недоступен.");
		}
		int key = 0;
		if (!method.equalsIgnoreCase("bruteforce")) {
			System.out.print("Введите ключ (целое число): ");
			key = Integer.parseInt(scanner.nextLine());

			if (dependencies.validator().isKeyValid(key)) {
				throw new IOException("Недопустимый ключ. Должен быть от 1 до " + (Alphabet.getLength() - 1));
			}
		}

		String outputFile = dependencies.fileManager().generateOutputFilePath(inputFile, method);

		Path parentDir = Paths.get(outputFile).getParent();
		if (!Files.isWritable(parentDir)) {
			dependencies.fileManager().createDirectory(parentDir);
			System.out.println("Создана директория: " + parentDir);
		}

		if (Files.isDirectory(Paths.get(outputFile))) {
			throw new IOException("Сгенерированный путь " + outputFile + " является директорией.");
		}

		String text = dependencies.fileManager().readFile(inputFile);
		String result = switch (method) {
			case "encrypted" -> dependencies.cipher().encrypt(text, key);
			case "decrypted" -> dependencies.cipher().decrypt(text, key);
			case "bruteforce" -> dependencies.decryptor().decrypt(text);
			default -> throw new IllegalArgumentException("Неизвестный метод: " + method);
		};
		dependencies.fileManager().writeFile(outputFile, result);
		System.out.println("Операция выполнена успешно. Результат сохранен в: " + outputFile);
	}

	private void printMenu() {
		System.out.println("\nМеню:");
		System.out.println("1. Зашифровать текст");
		System.out.println("2. Расшифровать текст с ключом");
		System.out.println("3. Расшифровать методом brute force");
		System.out.println("4. Расшифровать методом статистического анализа");
		System.out.println("0. Выход");
		System.out.print("Выберите действие: ");
	}
}
