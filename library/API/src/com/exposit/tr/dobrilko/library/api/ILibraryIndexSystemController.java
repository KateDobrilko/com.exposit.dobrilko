package com.exposit.tr.dobrilko.library.api;

public interface ILibraryIndexSystemController {

	public String findBookByAuthor(String author);

	public String findBookByNameAndAuthor(String author, String name);

	public String findBookByName(String name);

	public String orderBook(String subscriber, int index);

	public String returnBook(int index);

	public void loadAllBooks(String path);
	
    

}
