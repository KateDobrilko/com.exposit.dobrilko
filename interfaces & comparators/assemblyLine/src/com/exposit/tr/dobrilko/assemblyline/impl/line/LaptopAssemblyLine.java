package com.exposit.tr.dobrilko.assemblyline.impl.line;

import com.exposit.tr.dobrilko.assemblyline.impl.linestep.BodyLineStep;
import com.exposit.tr.dobrilko.assemblyline.impl.linestep.DisplayLineStep;
import com.exposit.tr.dobrilko.assemblyline.impl.linestep.MotherboardLineStep;
import com.exposit.tr.dobrilko.assemblyline.impl.product.Laptop;
import com.exposit.tr.dobrilko.assemblyline.interfaces.IAssemblyLine;
import com.exposit.tr.dobrilko.assemblyline.interfaces.IProduct;

public class LaptopAssemblyLine implements IAssemblyLine {
	private BodyLineStep bodyLineStep;
	private MotherboardLineStep motherboardLineStep;
	private DisplayLineStep displayLineStep;

	public LaptopAssemblyLine(BodyLineStep bodyLineStep,
			MotherboardLineStep motherboardLineStep,
			DisplayLineStep displayLineStep) {
		this.bodyLineStep = bodyLineStep;
		this.motherboardLineStep = motherboardLineStep;
		this.displayLineStep = displayLineStep;

	}

	@Override
	public IProduct installDetails(IProduct product) {
		if (product instanceof Laptop) {
			product.installFirstPart(bodyLineStep.createProductPart());
			product.installSecondPart(motherboardLineStep.createProductPart());
			product.installThirdPart(displayLineStep.createProductPart());

		}

		return product;
	}
}
