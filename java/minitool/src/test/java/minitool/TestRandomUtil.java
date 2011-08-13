package minitool;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class TestRandomUtil {

	@Test
	public void testSelect() {
		int[] weights = { 2, 1, 3, 0, 4 };
		int sum = MathUtil.sum(weights);
		Random r = new IntArrayRandom(MathUtil.range(0, sum));
		int[] expected = new int[] { 0, 0, 1, 2, 2, 2, 4, 4, 4, 4 };
		for (int i = 0; i < sum; i++) {
			Assert.assertEquals(expected[i], RandomUtil.randomSelect(r, weights));
		}
	}

	@SuppressWarnings("serial")
	public static class IntArrayRandom extends Random {

		private int[] seq;
		private int index;

		public IntArrayRandom(int[] seq) {
			this.seq = seq;
			this.index = 0;
		}

		@Override
		public int nextInt(int n) {
			return seq[index++];
		}

	}
}
