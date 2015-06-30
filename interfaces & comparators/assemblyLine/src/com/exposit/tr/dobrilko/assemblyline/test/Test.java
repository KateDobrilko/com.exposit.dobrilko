package com.exposit.tr.dobrilko.assemblyline.test;

import java.util.LinkedList;

import com.exposit.tr.dobrilko.assemblyline.comparator.ModelNameComparator;
import com.exposit.tr.dobrilko.assemblyline.comparator.PriceComparator;
import com.exposit.tr.dobrilko.assemblyline.impl.line.LaptopAssemblyLine;
import com.exposit.tr.dobrilko.assemblyline.impl.linestep.BodyLineStep;
import com.exposit.tr.dobrilko.assemblyline.impl.linestep.DisplayLineStep;
import com.exposit.tr.dobrilko.assemblyline.impl.linestep.MotherboardLineStep;
import com.exposit.tr.dobrilko.assemblyline.impl.product.Laptop;
import com.exposit.tr.dobrilko.assemblyline.interfaces.IAssemblyLine;
import com.exposit.tr.dobrilko.assemblyline.interfaces.ILineStep;
import com.exposit.tr.dobrilko.assemblyline.interfaces.IProduct;
import com.exposit.tr.dobrilko.assemblyline.interfaces.IProductPart;

public class Test {

	public static void main(String[] args) {

		IProduct laptop = new Laptop();
		ILineStep bodyLineStep = new BodyLineStep();
		ILineStep motherboardLineStep = new MotherboardLineStep();
		ILineStep displayLineStep = new DisplayLineStep();
		IAssemblyLine assemblyLine = new LaptopAssemblyLine(
				(BodyLineStep) bodyLineStep,
				(MotherboardLineStep) motherboardLineStep,
				(DisplayLineStep) displayLineStep);

		
		System.out.println();
		System.out.println("Is laptop body present?");
		System.out.println(((Laptop) laptop).getBody() != null);
		System.out.println("Is laptop display present?");
		System.out.println(((Laptop) laptop).getDisplay() != null);
		System.out.println("Is laptop motherboard present?");
		System.out.println(((Laptop) laptop).getMotherboard() != null);
		System.out.println();
		assemblyLine.installDetails(laptop);
		System.out.println();
		System.out.println("Is laptop body present?");
		System.out.println(((Laptop) laptop).getBody() != null);
		System.out.println("Is laptop display present?");
		System.out.println(((Laptop) laptop).getDisplay() != null);
		System.out.println("Is laptop motherboard present?");
		System.out.println(((Laptop) laptop).getMotherboard() != null);
		System.out.println();
		
		LinkedList<IProductPart> parts = new LinkedList<IProductPart>();
		parts.add(((Laptop) laptop).getBody());
		parts.add(((Laptop) laptop).getDisplay());
		parts.add(((Laptop) laptop).getMotherboard());
		for (IProductPart part : parts) {
			StringBuilder sb = new StringBuilder();
			sb.append(part.getClass().getSimpleName());
			sb.append(":");
			sb.append(part.getPrice());
			sb.append(",");
			sb.append(part.getModelName());
			sb.append(System.lineSeparator());
			System.out.print(sb.toString());
		}
		System.out.println();
		parts.sort(new ModelNameComparator());
		for (IProductPart part : parts) {
			StringBuilder sb = new StringBuilder();
			sb.append(part.getClass().getSimpleName());
			sb.append(":");
			sb.append(part.getPrice());
			sb.append(",");
			sb.append(part.getModelName());
			sb.append(System.lineSeparator());
			System.out.print(sb.toString());
		}
		parts.sort(new PriceComparator());
		System.out.println();
		for (IProductPart part : parts) {
			StringBuilder sb = new StringBuilder();
			sb.append(part.getClass().getSimpleName());
			sb.append(":");
			sb.append(part.getPrice());
			sb.append(",");
			sb.append(part.getModelName());
			sb.append(System.lineSeparator());
			System.out.print(sb.toString());
		}

	}

}
