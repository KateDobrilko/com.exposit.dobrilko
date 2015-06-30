package com.exposit.tr.dobrilko.assemblyline.comparator;

import java.util.Comparator;

import com.exposit.tr.dobrilko.assemblyline.interfaces.IPriceHolder;

public class PriceComparator implements Comparator<IPriceHolder> {

	@Override
	public int compare(IPriceHolder a, IPriceHolder b) {

		return Double.compare(a.getPrice(), b.getPrice());
	}
}