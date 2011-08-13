package minitool;

import org.junit.Assert;

import org.junit.Test;

public class TestMathUtil {

	@Test
	public void testSum() {
		Assert.assertEquals(0, MathUtil.sum(new int[0]));
		Assert.assertEquals(10, MathUtil.sum(new int[] { 1, 2, 3, 4 }));
	}

	@Test
	public void testRange() {
		Assert.assertArrayEquals(new int[] { 1, 2, 3, 4 }, MathUtil.range(1, 5));
		Assert.assertArrayEquals(new int[] {}, MathUtil.range(1, 0));
		Assert.assertArrayEquals(new int[] {}, MathUtil.range(1, 1));
		Assert.assertArrayEquals(new int[] { 1 }, MathUtil.range(1, 2));
	}
}
