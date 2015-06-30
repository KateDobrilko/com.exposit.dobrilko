package com.exposit.tr.dobrilko.assemblyline.impl.linestep;

import com.exposit.tr.dobrilko.assemblyline.impl.part.Motherboard;
import com.exposit.tr.dobrilko.assemblyline.interfaces.ILineStep;
import com.exposit.tr.dobrilko.assemblyline.interfaces.IProductPart;

public class MotherboardLineStep implements ILineStep {

	@Override
	public IProductPart createProductPart() {
		return new Motherboard("IBM 200", 240.5);
	};
}
