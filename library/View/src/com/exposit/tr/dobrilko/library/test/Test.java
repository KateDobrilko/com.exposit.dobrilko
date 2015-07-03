package com.exposit.tr.dobrilko.library.test;

import com.exposit.tr.dobrilko.library.menu.MenuController;
import com.exposit.tr.dobrilko.library.textworker.TerminalWorker;

public class Test {

	public static void main(String[] args) {
		TerminalWorker.getInstance().printString(
				"Please, enter path to repository root:");

		MenuController mc = new MenuController(TerminalWorker.getInstance()
				.readString());
		while (mc.run() != "EXIT") {
			TerminalWorker.getInstance().printString(mc.run());
		}
	}

}
