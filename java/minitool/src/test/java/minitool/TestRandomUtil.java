package minitool;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TestRandomUtil {

	@Test
	public void testSelect() {
		try {
			RandomUtil.randomSelect(new int[0]);
			Assert.fail("Empty array. Must throw a exception.");
		} catch (IllegalArgumentException e) {
		}

		int[] weights = { 2, 1, 3, 0, 4 };
		int sum = MathUtil.sum(weights);

		int[] expected = new int[] { 0, 0, 1, 2, 2, 2, 4, 4, 4, 4 };
		for (int i = 0; i < sum; i++) {
			Random r = Mockito.mock(Random.class);
			Mockito.when(r.nextInt(sum)).thenReturn(i);
			Assert.assertEquals(expected[i],
					RandomUtil.randomSelect(r, weights));
		}
	}

}