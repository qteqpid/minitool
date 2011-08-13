package minitool.config;

import java.lang.reflect.InvocationTargetException;

import junit.framework.Assert;
import minitool.config.Config.NameType;
import minitool.config.Config.Property;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;

@SuppressWarnings("unused")
public class TestConfigFactory {

	@Config(strategy = NameType.Field)
	static class Config1 {
		@Property
		private int hello;
	}

	@Config(strategy = NameType.Field)
	static class Config2 {

		@Property(hasDefaultValue = true, defaultValue = "")
		private int wrongType;

	}

	@Config(strategy = NameType.Field)
	static class Config3 {

		@Property
		private int notExistProperty;

	}

	@Config(strategy = NameType.Field, ignoreNotFound = false)
	static class Config4 {

		@Property
		private int hello;
	}

	@Config(strategy = NameType.Field)
	static class Config5 {

		@Property(hasDefaultValue = true, defaultValue = "123")
		private int hello;
	}

	@Test
	public void testGetConfig() throws ConfigurationException, ConfigException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {

		Config1 config1 = ConfigFactory.getConfig(Config1.class, getClass()
				.getResource("config1.conf"));
		Assert.assertEquals(100, config1.hello);

		try {
			ConfigFactory.getConfig(Config2.class,
					getClass().getResource("config2.conf"));
			Assert.fail("must throw a ConfigException because of wrong type");
		} catch (ConfigException e) {

		}

		try {
			ConfigFactory.getConfig(Config3.class,
					getClass().getResource("config3.conf"));
			Assert.fail("must throw a ConfigException when a property has not a default value and does not config in file");
		} catch (ConfigException e) {

		}

		try {
			ConfigFactory.getConfig(Config4.class,
					getClass().getResource("config4.conf"));
			Assert.fail("must throw a ConfigException when ignoreNotFound == false and the file is not found");
		} catch (ConfigException e) {
		}

		Config5 config5 = ConfigFactory.getConfig(Config5.class, getClass()
				.getResource("config5.conf"));
		Assert.assertEquals(123, config5.hello);
	}
}
