package com.exposit.tr.dobrilko.library.read_update.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.exposit.tr.dobrilko.library.db_container.DbContainer;
import com.exposit.tr.dobrilko.library.db_container.LibraryContainer;
import com.exposit.tr.dobrilko.library.dbinterface.IDbController;
import com.exposit.tr.dobrilko.library.entity.Book;

public class CsvDbController implements IDbController {

	private Logger logger = Logger.getLogger(CsvDbController.class);

	private static CsvDbController instance;

	private CsvDbController() {

	}

	public static CsvDbController getInstance() {
		if (instance == null) {
			instance = new CsvDbController();
		}
		return instance;
	}

	@Override
	public void read(String path, String libraryName, String type) {
		File directory = new File(path);
		File[] files = directory.listFiles();
		ArrayList<String> strings = new ArrayList<String>();
		LibraryContainer lc = new LibraryContainer(path, libraryName, type);
		for (File file : files) {
			BufferedReader br = null;
			String line = "";

			try {

				br = new BufferedReader(new FileReader(file.getAbsolutePath()));
				while ((line = br.readLine()) != null) {

					strings.add(line);

				}

			} catch (FileNotFoundException e) {
				logger.error("File Not Found. Check your input,", e);
			} catch (IOException e) {
				logger.error("IOException catched!");
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						logger.error("IOException catched!");
					}
				}
			}

			int currentPointerPosition = 0;

			while (currentPointerPosition < strings.size()) {

				String[] infoLine = strings.get(currentPointerPosition).split(
						",");

				currentPointerPosition++;
				int id = Integer.parseInt(infoLine[0]);
				String author = infoLine[1].trim();
				String name = infoLine[2].trim();
				Calendar date = null;
				String abonent = null;
				if ((infoLine.length > 4) && (!infoLine[3].isEmpty())
						&& (!infoLine[4].isEmpty())) {

					String[] d = infoLine[3].trim().split("\\.");
					int year = Integer.parseInt(d[0]);
					int month = Integer.parseInt(d[1]);
					int day = Integer.parseInt(d[2]);
					date = new GregorianCalendar(year, month - 1, day);
					abonent = infoLine[4].trim();

				}
				Book book = new Book();
				book.setAuthor(author);
				book.setIndex(id);
				book.setIssueDate(date);
				book.setName(name);
				book.setSubscriber(abonent);
				book.setFilePath(file.getAbsolutePath());
				book.setLc(lc);

				lc.getBooks().add(book);

			}
		}

		DbContainer.getInstance().getLibs().add(lc);
	}

	@Override
	public void update(Book book) {
		try (FileWriter writer = new FileWriter(book.getFilePath(), false);) {
			StringBuilder sb = new StringBuilder();
			for (Book b : book.getLc().getBooks()) {
				if (b.getFilePath() == book.getFilePath()) {
					
					sb.append(book.getIndex());
					sb.append(",");
					sb.append(book.getAuthor());
					sb.append(",");
					sb.append(book.getName());
					sb.append(",");
					if (book.getIssueDate() != null) {
						sb.append(book.getIssueDate().get(Calendar.YEAR));
						sb.append(".");
						sb.append(book.getIssueDate().get(Calendar.MONTH) + 1);
						sb.append(".");
						sb.append(book.getIssueDate()
								.get(Calendar.DAY_OF_MONTH));
					}
					sb.append(",");
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
				writer.write("");
				writer.flush();
				writer.close();
				writer = new FileWriter(b.getFilePath(), true);

				StringBuilder sb = new StringBuilder();
				sb.append(b.getIndex());
				sb.append(",");
				sb.append(b.getAuthor());
				sb.append(",");
				sb.append(b.getName());
				sb.append(",");
				if (b.getIssueDate() != null) {
					sb.append(b.getIssueDate().toString());
				}
				sb.append(",");
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
