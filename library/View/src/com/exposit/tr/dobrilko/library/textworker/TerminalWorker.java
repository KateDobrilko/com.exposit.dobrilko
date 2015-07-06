package com.exposit.tr.dobrilko.library.textworker;

import java.util.Scanner;

public class TerminalWorker {
	static private TerminalWorker instance;

	private TerminalWorker() {

	}

	static public TerminalWorker getInstance() {
		if (instance == null) {
			instance = new TerminalWorker();
		}
		return instance;

	}

	
	public void printString(String result) {

		System.out.println(result);
	}

	public String readString() {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String command = sc.nextLine();
		return command;
	}

}
