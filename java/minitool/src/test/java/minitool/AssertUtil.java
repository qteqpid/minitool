package minitool;

import org.junit.Assert;

public class AssertUtil {

	public static void assertArrayEquals(String[] expected, String[] actual) {
		Assert.assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			Assert.assertEquals(expected[i], actual[i]);
		}
	}
}
