package com.exposit.tr.dobrilko.library.db_container;

import java.util.ArrayList;

public class DbContainer {

	static private DbContainer instance;
	private ArrayList<LibraryContainer> libs;

	private DbContainer() {
		libs = new ArrayList<LibraryContainer>();
	}

	static public DbContainer getInstance() {
		if (instance == null) {
			instance = new DbContainer();
		}
		return instance;
	}

	public ArrayList<LibraryContainer> getLibs() {
		return libs;
	}

	public void setLibs(ArrayList<LibraryContainer> libs) {
		this.libs = libs;
	}

}
