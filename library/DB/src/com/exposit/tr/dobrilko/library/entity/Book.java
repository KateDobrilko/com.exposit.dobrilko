package com.exposit.tr.dobrilko.library.entity;

import java.util.Date;

import com.exposit.tr.dobrilko.library.db_container.LibraryContainer;

public class Book {
	private int index;
	private String author;
	private String name;
	private Date issueDate;
	private String abonent;
	private String filePath;
	private LibraryContainer lc;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getSubscriber() {
		return abonent;
	}

	public void setSubscriber(String subscriber) {
		this.abonent = subscriber;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public LibraryContainer getLc() {
		return lc;
	}

	public void setLc(LibraryContainer lc) {
		this.lc = lc;
	}

}
