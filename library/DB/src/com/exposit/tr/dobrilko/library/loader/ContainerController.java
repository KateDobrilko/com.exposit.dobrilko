package com.exposit.tr.dobrilko.library.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.exposit.tr.dobrilko.library.db.api.IContainerController;
import com.exposit.tr.dobrilko.library.db_container.DbContainer;
import com.exposit.tr.dobrilko.library.db_container.LibraryContainer;
import com.exposit.tr.dobrilko.library.dbinterface.IDbController;
import com.exposit.tr.dobrilko.library.entity.Book;
import com.exposit.tr.dobrilko.library.read_update.impl.CsvDbController;
import com.exposit.tr.dobrilko.library.read_update.impl.TextFileDbController;

public class ContainerController implements IContainerController {

	private static ContainerController instance;

	private ContainerController() {

	}

	public static ContainerController getInstance() {
		if (instance == null) {
			instance = new ContainerController();
		}
		return instance;
	}

	public void loadLibraries(String path) {

		File root = new File(path);
		File[] libraryRepositories = root.listFiles();

		for (File repository : libraryRepositories) {
			String repositoryName = repository.getName();
			Pattern p = Pattern.compile("(CSV|Text)_(.*)");
			Matcher m = p.matcher(repositoryName);
			if (m.matches()) {
				String[] s = repositoryName.split("_");
				if (s.length > 1) {
					if (s[0].equals("CSV")) {
						IDbController controller = CsvDbController
								.getInstance();
						controller.read(repository.getAbsolutePath(), s[1],
								s[0]);
					}
					if (s[0].equals("Text")) {
						IDbController controller = TextFileDbController
								.getInstance();
						controller.read(repository.getAbsolutePath(), s[1],
								s[0]);

					}
				}
			}
		}
	}

	public void saveLibrary() {
		for (LibraryContainer lc : DbContainer.getInstance().getLibs()) {
			if (lc.getType().equals("CSV")) {
				CsvDbController.getInstance().save(lc);

			}

			if (lc.getType().equals("Text")) {
				TextFileDbController.getInstance().save(lc);

			}
		}

	}

	public void update(Book book) {

		if (book.getLc().getType().equals("CSV")) {
			CsvDbController.getInstance().update(book);

		}

		if (book.getLc().getType().equals("Text")) {
			TextFileDbController.getInstance().update(book);

		}

	}

	@Override
	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
		for (LibraryContainer lc : DbContainer.getInstance().getLibs()) {
			books.addAll(lc.getBooks());
		}
		return books;

	}
}
