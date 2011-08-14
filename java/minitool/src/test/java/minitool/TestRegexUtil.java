package minitool;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class TestRegexUtil {

	@Test
	public void testFind() {
		Pattern pattern = Pattern.compile("abc(\\d{3})abc",
				Pattern.CASE_INSENSITIVE);

		Assert.assertEquals("abc123abc",
				RegexUtil.find(pattern, "efgabc123abcefg"));

		Assert.assertNull(RegexUtil.find(pattern, "efgabc12abcefg"));

		Assert.assertEquals("123",
				RegexUtil.find(pattern, "efg(abc123abc)efg(abc321abc)efg", 1));

		try {
			RegexUtil.find(pattern, "efg(abc123abc)efg(abc321abc)efg", 2);
			Assert.fail("must throw a exception when group id is out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void testFindAll() {
		Pattern pattern = Pattern.compile("abc(\\d{3})abc",
				Pattern.CASE_INSENSITIVE);

		AssertUtil.assertArrayEquals(new String[] { "abc123abc" },
				RegexUtil.findAll(pattern, "efgabc123abcefg"));

		Assert.assertArrayEquals(new String[0],
				RegexUtil.findAll(pattern, "efgabc12abcefg"));

		AssertUtil.assertArrayEquals(new String[] { "123", "321" }, RegexUtil
				.findAll(pattern, "efg(abc123abc)efg(abc321abc)efg", 1));

		try {
			RegexUtil.findAll(pattern, "efg(abc123abc)efg(abc321abc)efg", 2);
			Assert.fail("must throw a exception when group id is out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
	}
}
