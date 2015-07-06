package com.exposit.tr.dobrilko.library.controller;

import java.util.ArrayList;
import java.util.Calendar;
import com.exposit.tr.dobrilko.library.api.ILibraryIndexSystemController;
import com.exposit.tr.dobrilko.library.db.api.IContainerController;
import com.exposit.tr.dobrilko.library.entity.Book;
import com.exposit.tr.dobrilko.library.loader.ContainerController;
import com.exposit.tr.dobrilko.library.result_composer.ResultComposer;

public class LibraryIndexSystemController implements
		ILibraryIndexSystemController {
	private IContainerController container = ContainerController.getInstance();
	private String path = "src\\com\\exposit\\tr\\dobrilko\\library\\repository\\Libraries";

	public LibraryIndexSystemController() {
		container.loadLibraries(path);
	}

	@Override
	public String findBookByAuthor(String author) {
		// TODO
		ArrayList<Book> books = new ArrayList<Book>();

		for (Book book : container.getAllBooks()) {
			if (book.getAuthor().trim().equals(author)) {
				books.add(book);
			}
		}
		return ResultComposer.getInstance().prepareFindAnswer(books);

	}

	@Override
	public String findBookByNameAndAuthor(String author, String name) {

		// TODO
		ArrayList<Book> books = new ArrayList<Book>();

		for (Book book : container.getAllBooks()) {
			if ((book.getAuthor().equals(author))
					&& (book.getName().equals(author))) {
				books.add(book);
			}
		}
		return ResultComposer.getInstance().prepareFindAnswer(books);
	}

	@Override
	public String findBookByName(String name) {
		// TODO
		ArrayList<Book> books = new ArrayList<Book>();

		for (Book book : container.getAllBooks()) {
			if (book.getName().equals(name)) {
				books.add(book);
			}
		}
		return ResultComposer.getInstance().prepareFindAnswer(books);
	}

	@Override
	public String orderBook(String subscriber, int index) {
		Boolean flag = null;
		Book book = null;
		Calendar issueDate=null;
		String s = null;
		for (Book b : container.getAllBooks()) {
			if (b.getIndex() == index) {
				flag = false;
				if (b.getSubscriber() == null) {
					s = subscriber;
					book = b;
					b.setIssueDate(Calendar.getInstance());
					b.setSubscriber(subscriber);
					flag = true;
					issueDate = b.getIssueDate();
					container.update(book);
				}
			}
			if ((flag != null) && (flag.equals(false))) {
				s = b.getSubscriber();
				issueDate=b.getIssueDate();
			}
		}

		

		return ResultComposer.getInstance().prepareOrderAnswer(flag, s,
				issueDate);

	}

	@Override
	public String returnBook(int index) {
		Boolean flag = null;
		Book book = null;
		String abonent=null;
		
		for (Book b : container.getAllBooks()) {
			if (b.getIndex() == index) {
				flag = false;
				if (b.getSubscriber() != null) {
					
					book = b;
					abonent = b.getSubscriber();
					b.setIssueDate(null);
					b.setSubscriber(null);
					flag = true;
					
					container.update(book);
				}
			}
			if ((flag != null) && (flag.equals(false))) {
				
			}
		}

		

		return ResultComposer.getInstance().prepareReturnAnswer(flag, abonent);
	}

	@Override
	public void loadAllBooks(String path) {
		container.loadLibraries(path);

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
