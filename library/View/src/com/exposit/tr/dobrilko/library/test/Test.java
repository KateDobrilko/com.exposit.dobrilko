package com.exposit.tr.dobrilko.library.test;

import com.exposit.tr.dobrilko.library.menu.MenuController;

public class Test {

	public static void main(String[] args) {

		MenuController mc = MenuController.getInstance();
		while (mc.isExit() != true) {
			mc.run();
		}
	}

}
