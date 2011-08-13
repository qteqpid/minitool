package minitool.config;

import java.lang.reflect.Field;

public class ClassFieldNameStrategy implements NameStrategy {

	@Override
	public <T> String getName(Class<T> clazz, Field field) {
		return clazz.getSimpleName() + "." + field.getName();
	}

}
