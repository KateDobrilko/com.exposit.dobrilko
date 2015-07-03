package com.exposit.tr.dobrilko.library.menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.exposit.tr.dobrilko.library.api.ILibraryIndexSystemController;
import com.exposit.tr.dobrilko.library.controller.LibraryIndexSystemController;
import com.exposit.tr.dobrilko.library.textworker.TerminalWorker;

public class MenuController {

	private String path;

	public MenuController(String path) {
		this.path = path;
	}

	private ILibraryIndexSystemController controller = new LibraryIndexSystemController(
			path);

	public String executeCommand(String command) {
		String result = null;
		Pattern find = Pattern.compile("/FIND.*/g");
		Pattern order = Pattern.compile("/ORDER.*/g");
		Pattern returning = Pattern.compile("/RETURN.*/g");
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
			for (String attribute : attributes) {
				String[] parsedAttribute = attribute.split("=| ");
				if ((parsedAttribute.length > 1)) {
					int index = 0;
					String abonent = null;
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
		}

		return result;

	}

	public String run() {
		return executeCommand(TerminalWorker.getInstance().readString());

	}
}