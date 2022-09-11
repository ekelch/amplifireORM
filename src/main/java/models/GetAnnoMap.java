package models;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import annotations.GeneratedId;
import annotations.GeneratedIdSetter;
import annotations.NonId;
import annotations.NonIdGetter;

public class GetAnnoMap {

	
	public static Map<Integer, String> getNonIdFields(Object entry) {
		Field[] fields = entry.getClass().getDeclaredFields();
		Map<Integer, String> fieldAnnoMap = new HashMap<Integer, String>();
		
		for (Field field:fields) {
			if (field.isAnnotationPresent(NonId.class)) {
				NonId anno = field.getAnnotation(NonId.class);
				int paramKey = anno.column();
				String paramValue = field.getName();
				fieldAnnoMap.put(paramKey, paramValue);
			}	
		}
		return fieldAnnoMap;
	}
	
	public static Map<Integer, String> getIdFields(Object entry) {
		Field[] fields = entry.getClass().getDeclaredFields();
		Map<Integer, String> fieldAnnoMap = new HashMap<Integer, String>();
		
		for (Field field:fields) {
			if (field.isAnnotationPresent(GeneratedId.class)) {
				GeneratedId anno = field.getAnnotation(GeneratedId.class);
				int paramKey = anno.column();
				String paramValue = field.getName();
				fieldAnnoMap.put(paramKey, paramValue);
			}	
		}
		return fieldAnnoMap;
	}
	
	public static Map<Integer, String> getNonIdGetters(Object entry) {
		Method[] methods = entry.getClass().getDeclaredMethods();
		Map<Integer, String> methodAnnoMap = new HashMap<Integer, String>();
		
		for (Method method:methods) {
			if (method.isAnnotationPresent(NonIdGetter.class))
				try{
					Object obj = method.invoke(entry);
					NonIdGetter anno = method.getAnnotation(NonIdGetter.class);
					int paramKey = anno.column();
    				String paramValue = obj.toString();
    				methodAnnoMap.put(paramKey, paramValue);
					
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
					e.printStackTrace();
				}
		}
		return methodAnnoMap;
	}
	
	public static Map<Integer, Method> getIdSetters(Object entry) {
		Method[] methods = entry.getClass().getDeclaredMethods();
		Map<Integer, Method> methodAnnoMap = new HashMap<Integer, Method>();
		
		for (Method method:methods) {
			if (method.isAnnotationPresent(GeneratedIdSetter.class))
				try{
					GeneratedIdSetter anno = method.getAnnotation(GeneratedIdSetter.class);
					int paramKey = anno.column();
    				methodAnnoMap.put(paramKey, method);
					
				} catch (IllegalArgumentException e){
					e.printStackTrace();
				}
		}
		return methodAnnoMap;
	}
	
	
}
