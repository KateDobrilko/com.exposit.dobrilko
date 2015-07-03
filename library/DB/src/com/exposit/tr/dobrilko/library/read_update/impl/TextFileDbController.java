package com.exposit.tr.dobrilko.library.read_update.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
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

	@SuppressWarnings("deprecation")
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
			book.setAuthor(p.getProperty("Author"));
			book.setIndex(Integer.parseInt(p.getProperty("Index")));
			if (p.getProperty("Issued") != "") {
				book.setIssueDate(new Date(p.getProperty("Issued")));
			}
			book.setName(p.getProperty("Name"));
			book.setSubscriber(p.getProperty("Issuedto"));
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
				if (b.getFilePath() == book.getFilePath()) {
					StringBuilder sb = new StringBuilder();
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
						sb.append(book.getIssueDate().toString());
					}
					sb.append(System.lineSeparator());
					sb.append("Issuedto=");
					if (book.getSubscriber() != null) {
						sb.append(book.getSubscriber());
					}
					sb.append(System.lineSeparator());

					writer.write(sb.toString());
				}
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
