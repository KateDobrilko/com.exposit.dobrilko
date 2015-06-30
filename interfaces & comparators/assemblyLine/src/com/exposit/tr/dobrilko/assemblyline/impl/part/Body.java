package com.exposit.tr.dobrilko.assemblyline.impl.part;

import com.exposit.tr.dobrilko.assemblyline.interfaces.IProductPart;

public class Body implements IProductPart {

	private Double price;
	private String modelName;

	public Body(String modelName, Double price) {
		this.setPrice(price);
		this.setModelName(modelName);
	}

	@Override
	public Double getPrice() {
		return this.price;
	}

	@Override
	public String getModelName() {
		return this.modelName;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
