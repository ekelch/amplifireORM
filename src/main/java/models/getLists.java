package models;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import annotations.NonIdGetter;

public class getLists {

	public static List<Method> getNonIdGetters(Object entry) {
		Method[] methods = entry.getClass().getDeclaredMethods();
		List<Method> getters = new ArrayList<Method>();
		for (Method method:methods) {
			if (method.isAnnotationPresent(NonIdGetter.class)) {
				getters.add(method);
			}
		}
		return getters;
	}
	
}
