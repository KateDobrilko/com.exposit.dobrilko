package com.exposit.tr.dobrilko.mi.test;

import java.util.LinkedList;

import com.exposit.tr.dobrilko.mi.model.BassGuitar;
import com.exposit.tr.dobrilko.mi.model.Drum;
import com.exposit.tr.dobrilko.mi.model.Maracas;
import com.exposit.tr.dobrilko.mi.model.MusicalInstrument;

public class Test {

	public static void main(String[] args) {

		LinkedList<MusicalInstrument> musicalInstruments = new LinkedList<MusicalInstrument>();
		StringBuilder sb = new StringBuilder();
		musicalInstruments.add(new Drum());
		musicalInstruments.add(new Maracas());
		musicalInstruments.add(new BassGuitar());
		for (MusicalInstrument mi : musicalInstruments) {

			sb.append(mi.getClass().getSimpleName());
			sb.append(": ");
			sb.append(mi.play());
			sb.append(System.lineSeparator());

		}

		System.out.println(sb.toString());
	}
}
