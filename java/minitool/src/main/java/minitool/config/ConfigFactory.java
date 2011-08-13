package minitool.config;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import minitool.config.Config.Property;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

public class ConfigFactory {

	public static class Option {
		public boolean isIgnoreFileNotFoundError() {
			return ignoreFileNotFoundError;
		}

		public void setIgnoreFileNotFoundError(boolean ignoreFileNotFoundError) {
			this.ignoreFileNotFoundError = ignoreFileNotFoundError;
		}

		public File getFile() {
			return file;
		}

		public void setFile(File file) {
			this.file = file;
		}

		public URL getUrl() {
			return url;
		}

		public void setUrl(URL url) {
			this.url = url;
		}

		protected boolean ignoreFileNotFoundError;
		protected File file;
		protected URL url;

	}

	public static <T> T getConfig(Class<T> clazz, File file)
			throws ConfigException, InstantiationException,
			IllegalAccessException, ConfigurationException,
			InvocationTargetException {
		Option option = new Option();
		option.file = file;
		return getConfig(clazz, option);
	}

	public static <T> T getConfig(Class<T> clazz, URL url)
			throws ConfigException, InstantiationException,
			IllegalAccessException, ConfigurationException,
			InvocationTargetException {
		Option option = new Option();
		option.url = url;
		return getConfig(clazz, option);
	}

	public static <T> T getConfig(Class<T> clazz, String fileName)
			throws ConfigException, InstantiationException,
			IllegalAccessException, ConfigurationException,
			InvocationTargetException {
		return getConfig(clazz, new File(fileName));
	}

	public static <T> T getConfig(Class<T> clazz)
			throws InstantiationException, IllegalAccessException,
			ConfigException, InvocationTargetException, ConfigurationException {

		return getConfig(clazz, new Option());
	}

	public static <T> T getConfig(Class<T> clazz, Option option)
			throws InstantiationException, IllegalAccessException,
			ConfigException, InvocationTargetException, ConfigurationException {

		Config config = clazz.getAnnotation(Config.class);
		if (config == null) {
			throw new ConfigException(clazz.getName()
					+ " has not an annotation of @Config");
		}

		option.ignoreFileNotFoundError = config.ignoreNotFound();

		if (option.file == null && option.url == null) {
			option.file = new File(config.file());
			if (!option.file.exists()
					&& option.ignoreFileNotFoundError == false) {
				throw new ConfigException("Can not find the config file "
						+ option.file);
			}
		}

		NameStrategy nameStrategy;
		switch (config.strategy()) {
		case Field:
			nameStrategy = new FieldNameStrategy();
			break;
		case PackageClassField:
			nameStrategy = new PackageClassFieldNameStrategy();
			break;
		case ClassField:
		default:
			nameStrategy = new ClassFieldNameStrategy();
			break;
		}

		Configuration configuration;
		try {
			switch (config.type()) {
			case XML:
				if (option.url != null) {
					configuration = new XMLConfiguration(option.url);
				} else {
					configuration = new XMLConfiguration(option.file);
				}

				break;
			case Properties:
			default:
				if (option.url != null) {
					configuration = new PropertiesConfiguration(option.url);
				} else {
					configuration = new PropertiesConfiguration(option.file);
				}
				break;
			}
		} catch (ConfigurationException e) {
			if (option.ignoreFileNotFoundError) {
				configuration = new PropertiesConfiguration();
			} else {
				throw e;
			}
		}

		T obj = clazz.newInstance();
		ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
		convertUtilsBean.register(true, false, 0);
		for (Field field : clazz.getDeclaredFields()) {
			Property property = field.getAnnotation(Property.class);
			if (property != null) {
				field.setAccessible(true);

				String str = configuration.getString(nameStrategy.getName(
						clazz, field));
				if (str == null) {
					if (property.hasDefaultValue()) {
						str = property.defaultValue();
					} else {
						throw new ConfigException("Can not find "
								+ field.getName());
					}
				}

				try {
					Object value = convertUtilsBean.convert(str,
							field.getType());
					field.set(obj, value);
				} catch (ConversionException e) {
					throw new ConfigException(e.getMessage());
				}
			}
		}

		return obj;
	}
}
