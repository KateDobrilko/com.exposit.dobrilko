package com.exposit.tr.dobrilko.library.controller;

import java.util.ArrayList;
import java.util.Date;

import com.exposit.tr.dobrilko.library.api.ILibraryIndexSystemController;
import com.exposit.tr.dobrilko.library.db.api.IContainerController;
import com.exposit.tr.dobrilko.library.entity.Book;
import com.exposit.tr.dobrilko.library.loader.ContainerController;
import com.exposit.tr.dobrilko.library.result_composer.ResultComposer;

public class LibraryIndexSystemController implements
		ILibraryIndexSystemController {
	IContainerController container = ContainerController.getInstance();

	public LibraryIndexSystemController(String path) {
		container.loadLibraries(path);
	}

	@Override
	public String findBookByAuthor(String author) {
		// TODO
		ArrayList<Book> books = new ArrayList<Book>();

		for (Book book : container.getAllBooks()) {
			if (book.getAuthor().equals(author)) {
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
		Book book = null;
		for (Book b : container.getAllBooks()) {
			if (b.getIndex() == index) {
				book = b;
			}
		}
		book.setIssueDate(new Date());
		book.setSubscriber(subscriber);
		// TODO
		return ResultComposer.getInstance().prepareOrderAnswer(false,
				subscriber, book.getIssueDate());

	}

	@Override
	public String returnBook(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadAllBooks(String path) {
		// TODO Auto-generated method stub

	}

}
