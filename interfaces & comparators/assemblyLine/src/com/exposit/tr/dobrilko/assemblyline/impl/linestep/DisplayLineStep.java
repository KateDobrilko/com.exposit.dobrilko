package com.exposit.tr.dobrilko.assemblyline.impl.linestep;

import com.exposit.tr.dobrilko.assemblyline.impl.part.Display;
import com.exposit.tr.dobrilko.assemblyline.interfaces.ILineStep;
import com.exposit.tr.dobrilko.assemblyline.interfaces.IProductPart;

public class DisplayLineStep implements ILineStep {

	@Override
	public IProductPart createProductPart() {
		return new Display("Samsung A15", 120.13);
	};
}
