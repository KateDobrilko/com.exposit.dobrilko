package com.exposit.tr.dobrilko.assemblyline.impl.product;

import com.exposit.tr.dobrilko.assemblyline.impl.part.*;
import com.exposit.tr.dobrilko.assemblyline.interfaces.IProduct;
import com.exposit.tr.dobrilko.assemblyline.interfaces.IProductPart;

public class Laptop implements IProduct {

	private Body body;
	private Motherboard motherboard;
	private Display display;

	public Laptop() {

		System.out.println("Laptop billet has been created.");
	}

	@Override
	public void installFirstPart(IProductPart firstPart) {
		if (firstPart instanceof Body) {
			setBody((Body) firstPart);
			System.out.println("Body has been installed.");
		}

	};

	@Override
	public void installSecondPart(IProductPart secondPart) {
		if (secondPart instanceof Motherboard) {
			setMotherboard((Motherboard) secondPart);
			System.out.println("Motherboard has been installed.");
		}

	};

	@Override
	public void installThirdPart(IProductPart thirdPart) {
		if (thirdPart instanceof Display) {
			setDisplay((Display) thirdPart);
			System.out.println("Display has been installed.");
		}
	}

	public Motherboard getMotherboard() {
		return motherboard;
	}

	public void setMotherboard(Motherboard motherboard) {
		this.motherboard = motherboard;
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	};

}