package com.exposit.tr.dobrilko.library.read_update.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.exposit.tr.dobrilko.library.db_container.DbContainer;
import com.exposit.tr.dobrilko.library.db_container.LibraryContainer;
import com.exposit.tr.dobrilko.library.dbinterface.IDbController;
import com.exposit.tr.dobrilko.library.entity.Book;

public class TextFileDbController implements IDbController {

	private Logger logger = Logger.getLogger(CsvDbController.class);

	private static TextFileDbController instance;

	private TextFileDbController() {

	}

	public static TextFileDbController getInstance() {
		if (instance == null) {
			instance = new TextFileDbController();
		}
		return instance;
	}

	@Override
	public void read(String path, String libraryName, String type) {
		File directory = new File(path);
		File[] files = directory.listFiles();
		LibraryContainer lc = new LibraryContainer(path, libraryName, type);
		for (File file : files) {
			Properties p = new Properties();

			try (FileInputStream fis = new FileInputStream(
					file.getAbsolutePath())) {

				p.load(fis);

			} catch (FileNotFoundException e) {
				logger.error("File Not Found. Check your input,", e);
			} catch (IOException e) {
				logger.error("IOException catched!");
			}

			Book book = new Book();
			book.setAuthor(p.getProperty("Author").trim());
			book.setIndex(Integer.parseInt(p.getProperty("Index").trim()));
			if (!p.getProperty("Issued").isEmpty()) {
				String[] d = p.getProperty("Issued").split("\\.");
				int year = Integer.parseInt(d[0].trim());
				int month = Integer.parseInt(d[1].trim());
				int day = Integer.parseInt(d[2].trim());
				book.setIssueDate(new GregorianCalendar(year, month - 1, day));
			}
			book.setName(p.getProperty("Name").trim());
			book.setSubscriber(p.getProperty("Issuedto").trim());
			book.setFilePath(file.getAbsolutePath());
			book.setLc(lc);

			lc.getBooks().add(book);

		}

		DbContainer.getInstance().getLibs().add(lc);
	}

	@Override
	public void update(Book book) {
		try (FileWriter writer = new FileWriter(book.getFilePath(), false);) {

			for (Book b : book.getLc().getBooks()) {
				StringBuilder sb = new StringBuilder();
				if (b.getFilePath() == book.getFilePath()) {

					sb.append("Index=");
					sb.append(book.getIndex());
					sb.append(System.lineSeparator());
					sb.append("Author=");
					sb.append(book.getAuthor());
					sb.append(System.lineSeparator());
					sb.append("Name=");
					sb.append(book.getName());
					sb.append(System.lineSeparator());
					sb.append("Issued=");
					if (book.getIssueDate() != null) {
						sb.append(book.getIssueDate().get(Calendar.YEAR));
						sb.append(".");
						sb.append(book.getIssueDate().get(Calendar.MONTH) + 1);
						sb.append(".");
						sb.append(book.getIssueDate()
								.get(Calendar.DAY_OF_MONTH));
					}
					sb.append(System.lineSeparator());
					sb.append("Issuedto=");
					if (book.getSubscriber() != null) {
						sb.append(book.getSubscriber());
					}
					sb.append(System.lineSeparator());

				}
				writer.write(sb.toString());
			}
		} catch (IOException e) {
			logger.error(e);
		}

	}

	@Override
	public void save(LibraryContainer lc) {

		for (Book b : lc.getBooks()) {
			try {
				FileWriter writer = new FileWriter(b.getFilePath(), false);

				StringBuilder sb = new StringBuilder();
				sb.append("Index=");
				sb.append(b.getIndex());
				sb.append(System.lineSeparator());
				sb.append("Author=");
				sb.append(b.getAuthor());
				sb.append(System.lineSeparator());
				sb.append("Name=");
				sb.append(b.getName());
				sb.append(System.lineSeparator());
				sb.append("Issued=");
				if (b.getIssueDate() != null) {
					sb.append(b.getIssueDate().toString());
				}
				sb.append(System.lineSeparator());
				sb.append("Issuedto=");
				if (b.getSubscriber() != null) {
					sb.append(b.getSubscriber());
				}
				sb.append(System.lineSeparator());

				writer.write(sb.toString());
				writer.flush();
				writer.close();

			} catch (IOException e) {
				logger.error(e);

			}

		}
	}
}
