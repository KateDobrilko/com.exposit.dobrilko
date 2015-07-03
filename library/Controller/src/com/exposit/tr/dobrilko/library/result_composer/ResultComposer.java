package com.exposit.tr.dobrilko.library.result_composer;

import java.util.ArrayList;
import java.util.Date;

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
		if (books != null) {
			for (Book book : books) {

				sb.append("FOUND ");
				sb.append("id=");
				sb.append(book.getIndex());
				sb.append(" lib=");
				sb.append(book.getLc().getLibraryName());
				sb.append(System.lineSeparator());
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

	public String prepareOrderAnswer(Boolean reserved, String abonent, Date date) {
		StringBuilder sb = new StringBuilder();
		if (reserved != null) {
			if (reserved == true) {
				sb.append("RESERVED ");
				sb.append("abonent=");
				sb.append(abonent);
				sb.append(" date=");
				sb.append(date.toString());
				sb.append(System.lineSeparator());
				sb.append(System.lineSeparator());
			}
			if (reserved == false) {
				sb.append("OK ");
				sb.append("abonent=");
				sb.append(abonent);
				sb.append(" date=");
				sb.append(date.toString());
				sb.append(System.lineSeparator());
			}
		} else {
			sb.append("NOTFOUND");
			sb.append(System.lineSeparator());
		}
		return sb.toString();

	}

}
