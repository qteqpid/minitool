package minitool.config;

import org.junit.Assert;
import org.junit.Test;

public class TestClassFieldNameStrategy {

	@Test
	public void testGetName() throws SecurityException, NoSuchFieldException {
		NameStrategy nameStrategy = new ClassFieldNameStrategy();

		Assert.assertEquals(
				"TempClass.tempField",
				nameStrategy.getName(TempClass.class,
						TempClass.class.getDeclaredField("tempField")));
	}

	class TempClass {
		@SuppressWarnings("unused")
		private int tempField;
	}
}
