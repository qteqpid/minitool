package minitool.config;

import java.lang.reflect.Field;

public class FieldNameStrategy implements NameStrategy {

	@Override
	public <T> String getName(Class<T> clazz, Field field) {
		return field.getName();
	}

}
