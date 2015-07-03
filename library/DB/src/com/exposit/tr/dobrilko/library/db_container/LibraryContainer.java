package com.exposit.tr.dobrilko.library.db_container;

import java.util.ArrayList;

import com.exposit.tr.dobrilko.library.entity.Book;

public class LibraryContainer {
	private String libraryName;
	private String path;
	private String type;
	private ArrayList<Book> books;

	public LibraryContainer(String path, String name, String type) {
		this.setBooks(new ArrayList<Book>());
		this.setLibraryName(name);
		this.setType(type);
		this.setPath(path);
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
