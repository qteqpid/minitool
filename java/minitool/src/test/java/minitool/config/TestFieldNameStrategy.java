package minitool.config;

import org.junit.Assert;
import org.junit.Test;

public class TestFieldNameStrategy {

	@Test
	public void testGetName() throws SecurityException, NoSuchFieldException {
		NameStrategy nameStrategy = new FieldNameStrategy();

		Assert.assertEquals(
				"tempField",
				nameStrategy.getName(TempClass.class,
						TempClass.class.getDeclaredField("tempField")));
	}

	class TempClass {
		@SuppressWarnings("unused")
		private int tempField;
	}
}
