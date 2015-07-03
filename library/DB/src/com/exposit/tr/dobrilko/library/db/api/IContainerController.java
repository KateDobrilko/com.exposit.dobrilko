package com.exposit.tr.dobrilko.library.db.api;

import java.util.ArrayList;

import com.exposit.tr.dobrilko.library.db_container.DbContainer;
import com.exposit.tr.dobrilko.library.entity.Book;

public interface IContainerController {
	public void loadLibraries(String path);

	public void saveLibrary();

	public void update(Book book);

	public ArrayList<Book> getAllBooks();
}
