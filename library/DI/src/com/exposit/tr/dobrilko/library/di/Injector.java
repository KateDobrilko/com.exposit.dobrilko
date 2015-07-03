package com.exposit.tr.dobrilko.library.di;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.exposit.tr.dobrilko.library.api.ILibraryIndexSystemController;
import com.exposit.tr.dobrilko.library.di.annotation.Inject;
import com.exposit.tr.dobrilko.library.di.enumeration.ControllerClass;
import com.exposit.tr.dobrilko.library.property.PropertyStorage;

public class Injector {
	public static <T> void inject(T object) {
		final Field[] declaredFields = object.getClass().getDeclaredFields();
		ArrayList<Field> fieldsToInject = new ArrayList<Field>();
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(Inject.class)) {
				fieldsToInject.add(field);
			}
		}
		for (Field field : fieldsToInject) {
			String injectionClassName = null;
			Inject inject = field.getAnnotation(Inject.class);
			if (inject.controllerClass().equals(
					ControllerClass.LIBRARY_INDEX_SYSTEM_CONTROLLER)) {
				injectionClassName = PropertyStorage.getInstance()
						.getBookshopControllerName();

			}
			field.setAccessible(true);
			try {
				if (field.getType().isAssignableFrom(
						Class.forName(injectionClassName))) {
					try {
						ILibraryIndexSystemController ic = (ILibraryIndexSystemController) Class
								.forName(injectionClassName).newInstance();
						field.set(object, ic);
					} catch (IllegalArgumentException | IllegalAccessException
							| InstantiationException | ClassNotFoundException e) {
						Logger logger = Logger.getLogger(Injector.class);
						logger.error("Error during injection!", e);
					}
				}
			} catch (ClassNotFoundException e) {
				Logger logger = Logger.getLogger(Injector.class);
				logger.error("Class not found!", e);
			}

			field.setAccessible(false);

		}

	}
}
