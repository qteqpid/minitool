package minitool.config;

import java.lang.reflect.InvocationTargetException;

import junit.framework.Assert;
import minitool.config.Config.NameType;
import minitool.config.Config.Property;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;

@Config(strategy = NameType.Field, file = "test")
public class TestConfig {

	@Property(hasDefaultValue = true, defaultValue = "1")
	private int hello;

	@Test
	public void test() throws ConfigurationException {
		try {
			TestConfig testConfig = ConfigFactory.getConfig(TestConfig.class);

			Assert.assertEquals(1, testConfig.hello);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
