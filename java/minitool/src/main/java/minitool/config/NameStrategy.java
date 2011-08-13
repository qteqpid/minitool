package minitool.config;

import java.lang.reflect.Field;

public interface NameStrategy {
	public <T> String getName(Class<T> clazz, Field field);
}
