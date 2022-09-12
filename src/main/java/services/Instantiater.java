package services;

import java.lang.reflect.Constructor;

public class Instantiater {

	public static Object construct(Class<?> clazz) {
		Object obj = null;
		try {
			Constructor<?> construct = clazz.getConstructor();
			obj = construct.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return obj;
	}
}
