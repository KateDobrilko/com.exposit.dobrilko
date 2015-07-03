package com.exposit.tr.dobrilko.library.dbinterface;

import com.exposit.tr.dobrilko.library.db_container.LibraryContainer;
import com.exposit.tr.dobrilko.library.entity.Book;

public interface IDbController {
	public void read(String path, String name, String type);

	public void update(Book book);

	void save(LibraryContainer lc);
}
