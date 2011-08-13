package minitool.config;

import java.lang.reflect.Field;

public class PackageClassFieldNameStrategy implements NameStrategy {

	@Override
	public <T> String getName(Class<T> clazz, Field field) {
		return clazz.getCanonicalName() + "." + field.getName();
	}

}
