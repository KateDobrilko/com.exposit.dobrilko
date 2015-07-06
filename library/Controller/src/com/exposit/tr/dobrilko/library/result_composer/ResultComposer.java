package com.exposit.tr.dobrilko.library.result_composer;

import java.util.ArrayList;
import java.util.Calendar;
import com.exposit.tr.dobrilko.library.entity.Book;

public class ResultComposer {
	static private ResultComposer instance;

	private ResultComposer() {

	}

	static public ResultComposer getInstance() {
		if (instance == null) {
			instance = new ResultComposer();
		}
		return instance;
	}

	public String prepareFindAnswer(ArrayList<Book> books) {
		StringBuilder sb = new StringBuilder();
		if (!books.isEmpty()) {
			int missing = 0;
			for (Book book : books) {
				if (!book.getSubscriber().isEmpty()) {
					missing++;
				}

			}
			if (missing != books.size()) {
				for (Book book : books) {
					if (book.getSubscriber() == null) {
						sb.append("FOUND ");
						sb.append("id=");
						sb.append(book.getIndex());
						sb.append(" lib=");
						sb.append(book.getLc().getLibraryName());
						sb.append(System.lineSeparator());
					}
				}
			} else {
				for (Book book : books) {

					sb.append("FOUNDMISSING ");
					sb.append("id=");
					sb.append(book.getIndex());
					sb.append(" lib=");
					sb.append(book.getLc().getLibraryName());
					sb.append(" issued=");

					sb.append(book.getIssueDate().get(Calendar.DAY_OF_MONTH));
					sb.append(".");
					sb.append(book.getIssueDate().get(Calendar.MONTH)+1);
					sb.append(".");
					sb.append(book.getIssueDate().get(Calendar.YEAR));

					sb.append(System.lineSeparator());
				}
			}
		} else {
			sb.append("NOTFOUND");
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public String prepareReturnAnswer(Boolean returned, String abonent) {
		StringBuilder sb = new StringBuilder();
		if (returned != null) {
			if (returned == false) {
				sb.append("ALREADYRETURNED");
				sb.append(System.lineSeparator());
			}
			if (returned == true) {
				sb.append("OK ");
				sb.append("abonent=");
				sb.append(abonent);
				sb.append(System.lineSeparator());
			}
		} else {
			sb.append("NOTFOUND");
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public String prepareOrderAnswer(Boolean reserved, String abonent,
			Calendar calendar) {
		StringBuilder sb = new StringBuilder();
		if (reserved != null) {
			if (reserved == false) {
				sb.append("RESERVED ");
				sb.append("abonent=");
				sb.append(abonent);
				sb.append(" date=");
				sb.append(calendar.get(Calendar.DAY_OF_MONTH));
				sb.append(".");
				sb.append(calendar.get(Calendar.MONTH)+1);
				sb.append(".");
				sb.append(calendar.get(Calendar.YEAR));
				sb.append(System.lineSeparator());
				sb.append(System.lineSeparator());
			}
			if (reserved == true) {
				sb.append("OK ");
				sb.append("abonent=");
				sb.append(abonent);
				sb.append(" date=");
				sb.append(calendar.get(Calendar.DAY_OF_MONTH));
				sb.append(".");
				sb.append(calendar.get(Calendar.MONTH)+1);
				sb.append(".");
				sb.append(calendar.get(Calendar.YEAR));
				sb.append(System.lineSeparator());
			}
		} else {
			sb.append("NOTFOUND");
			sb.append(System.lineSeparator());
		}
		return sb.toString();

	}

}
