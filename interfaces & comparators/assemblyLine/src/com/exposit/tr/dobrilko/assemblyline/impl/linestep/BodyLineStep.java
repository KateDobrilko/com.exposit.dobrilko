package com.exposit.tr.dobrilko.assemblyline.impl.linestep;

import com.exposit.tr.dobrilko.assemblyline.impl.part.Body;
import com.exposit.tr.dobrilko.assemblyline.interfaces.ILineStep;
import com.exposit.tr.dobrilko.assemblyline.interfaces.IProductPart;

public class BodyLineStep implements ILineStep {

	@Override
	public IProductPart createProductPart() {
		return new Body("K200", 160.5);
	};
}
