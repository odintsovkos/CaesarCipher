package ru.odinkos;

import ru.odinkos.di.DependencyContainer;
import ru.odinkos.ui.ConsoleInterface;

public class Main {
	public static void main(String[] args) {
		DependencyContainer container = new DependencyContainer();
		ConsoleInterface consoleInterface = new ConsoleInterface(container.dependencies);
		consoleInterface.run();
	}
}