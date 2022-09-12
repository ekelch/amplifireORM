package models;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import annotations.GeneratedId;
import annotations.GeneratedIdSetter;
import annotations.NonId;
import annotations.NonIdGetter;
import annotations.NonIdSetter;

public class getLists {

	public static List<Field> getGeneratedIds(Object entry) {
		Field[] fields = entry.getClass().getDeclaredFields();
		List<Field> generatedIds = new ArrayList<Field>();
		for (Field field:fields) {
			if (field.isAnnotationPresent(GeneratedId.class)) {
				generatedIds.add(field);
			}
		}
		return generatedIds;
	}
	
	public static List<Field> getNonIdFields(Object entry) {
		Field[] fields = entry.getClass().getDeclaredFields();
		List<Field> nonIdFields = new ArrayList<Field>();
		for (Field field:fields) {
			if (field.isAnnotationPresent(NonId.class)) {
				nonIdFields.add(field);
			}
		}
		return nonIdFields;
	}
	
	public static List<Field> getAllFields(Object entry) {
		Field[] fields = entry.getClass().getDeclaredFields();
		List<Field> allFields = new ArrayList<Field>();
		for (Field field:fields) {
			allFields.add(field);
		}
		return allFields;
	}
	
	public static List<String> getAllFieldNames(Object entry) {
		Field[] fields = entry.getClass().getDeclaredFields();
		List<String> allFields = new ArrayList<String>();
		for (Field field:fields) {
			allFields.add(field.getName());
		}
		return allFields;
	}
	
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
	
	public static List<Method> getNonIdSetters(Object entry) {
		Method[] methods = entry.getClass().getDeclaredMethods();
		List<Method> setters = new ArrayList<Method>();
		for (Method method:methods) {
			if (method.isAnnotationPresent(NonIdSetter.class)) {
				setters.add(method);
			}
		}
		return setters;
	}
	
	public static List<Method> getIdSetters(Object entry) {
		Method[] methods = entry.getClass().getDeclaredMethods();
		List<Method> setters = new ArrayList<Method>();
		for (Method method:methods) {
			if (method.isAnnotationPresent(GeneratedIdSetter.class)) {
				setters.add(method);
			}
		}
		return setters;
	}
	
	public static List<Method> getAllSetters(Object entry) {
		Method[] methods = entry.getClass().getDeclaredMethods();
		List<Method> setters = new ArrayList<Method>();
		for (Method method:methods) {
			if (method.isAnnotationPresent(GeneratedIdSetter.class) || method.isAnnotationPresent(NonIdSetter.class)) {
				setters.add(method);
			}
		}
		return setters;
	}
	
}
