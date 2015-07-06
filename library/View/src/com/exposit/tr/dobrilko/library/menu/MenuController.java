package com.exposit.tr.dobrilko.library.menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.exposit.tr.dobrilko.library.api.ILibraryIndexSystemController;
import com.exposit.tr.dobrilko.library.di.Injector;
import com.exposit.tr.dobrilko.library.di.annotation.Inject;
import com.exposit.tr.dobrilko.library.di.enumeration.ControllerClass;
import com.exposit.tr.dobrilko.library.textworker.TerminalWorker;

public class MenuController {

	@Inject(controllerClass = ControllerClass.LIBRARY_INDEX_SYSTEM_CONTROLLER)
	private static ILibraryIndexSystemController controller;

	private static MenuController instance;
	private static boolean exit = false;

	private MenuController() {

	}

	static public MenuController getInstance() {
		if (instance == null) {
			instance = new MenuController();
		}
		Injector.inject(instance);
		return instance;
	}

	public String executeCommand(String command) {
		String result = null;
		Pattern find = Pattern.compile("FIND.*");
		Pattern order = Pattern.compile("ORDER.*");
		Pattern returning = Pattern.compile("RETURN.*");
		Matcher findMatcher = find.matcher(command);
		Matcher orderMatcher = order.matcher(command);
		Matcher returnMatcher = returning.matcher(command);
		if (findMatcher.matches()) {
			String[] attributes = command.split(" ");
			for (String attribute : attributes) {
				String[] parsedAttribute = attribute.split("=| ");
				if ((parsedAttribute.length > 1)) {
					String author = null;
					String name = null;
					if (parsedAttribute[0].equals("author")) {
						author = parsedAttribute[1];
					}

					if (parsedAttribute[0].equals("name")) {
						name = parsedAttribute[1];
					}

					if ((author == null) && (name != null)) {
						result = controller.findBookByName(name);
					}

					if ((author != null) && (name == null)) {
						result = controller.findBookByAuthor(author);
					}

					if ((author != null) && (name != null)) {
						result = controller.findBookByNameAndAuthor(author,
								name);
					}
				}
			}

		}

		if (orderMatcher.matches()) {
			String[] attributes = command.split(" ");
			int index = 0;
			String abonent = null;
			for (String attribute : attributes) {
			
				String[] parsedAttribute = attribute.split("=| ");
				if ((parsedAttribute.length > 1)) {
					
					if (parsedAttribute[0].equals("id")) {
						index = Integer.parseInt(parsedAttribute[1]);
					}

					if (parsedAttribute[0].equals("abonent")) {
						abonent = parsedAttribute[1];
					}

					if ((abonent != null) && (index != 0)) {
						result = controller.orderBook(abonent, index);
					}
				}
			}

		}

		if (returnMatcher.matches()) {
			String[] attributes = command.split(" ");
			for (String attribute : attributes) {
				String[] parsedAttribute = attribute.split("=| ");
				if ((parsedAttribute.length > 1)) {
					int id = 0;

					if (parsedAttribute[0].equals("id")) {
						id = Integer.parseInt(parsedAttribute[1]);
					}

					if ((id != 0)) {
						result = controller.returnBook(id);
					}
				}
			}

		}

		if (command.equals("EXIT")) {
			result = command;
			exit = true;
		}

		return result;

	}

	public void run() {
		TerminalWorker
				.getInstance()
				.printString(
						"Please, type 'Y' if you want to use default repository path ot type 'N' to enter new: ");
		String s = TerminalWorker.getInstance().readString();
		if (s.equals("N")) {

			TerminalWorker.getInstance().printString("Enter path:");
			controller.loadAllBooks(TerminalWorker.getInstance().readString());
		}
		TerminalWorker.getInstance().printString("Please, input command:");

		TerminalWorker.getInstance().printString(
				executeCommand(TerminalWorker.getInstance().readString()));

	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		MenuController.exit = exit;
	}
}