package minitool.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Config {

	public enum NameType {
		Field, ClassField, PackageClassField
	}

	public NameType strategy() default NameType.ClassField;

	public String file() default "";

	public boolean ignoreNotFound() default true;

	public enum ConfigFileType {
		Properties, XML
	}

	public ConfigFileType type() default ConfigFileType.Properties;

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Property {

		public boolean hasDefaultValue() default false;

		public String defaultValue() default "";
	}

}
