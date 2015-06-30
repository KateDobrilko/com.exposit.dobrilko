package com.exposit.tr.dobrilko.assemblyline.comparator;

import java.util.Comparator;

import com.exposit.tr.dobrilko.assemblyline.interfaces.IModelNameHolder;

public class ModelNameComparator implements Comparator<IModelNameHolder> {

	@Override
	public int compare(IModelNameHolder a, IModelNameHolder b) {

		return a.getModelName().compareTo(b.getModelName());
	}
}
