package minitool;

import java.util.Random;

public class RandomUtil {

	private final static Random DEFAULT_RANDOM = new Random();

	/**
	 * 按照weights指定的比例随机选择其中的一项
	 * 
	 * @param weights
	 *            每个选项的比例
	 * @return [0, weights.length)之间的一个整数
	 */
	public static int randomSelect(int[] weights) {
		return randomSelect(DEFAULT_RANDOM, weights);
	}

	/**
	 * 按照weights指定的比例随机选择其中的一项
	 * 
	 * @param r
	 *            随机数生成器
	 * @param weights
	 *            每个选项的比例
	 * @return [0, weights.length)之间的一个整数
	 */
	public static int randomSelect(Random r, int[] weights) {
		if (weights.length == 0) {
			throw new IllegalArgumentException(
					"weights must not be an empty array");
		}

		int target = r.nextInt(MathUtil.sum(weights));
		int accumulator = 0;
		for (int i = 0; i < weights.length; i++) {
			accumulator += weights[i];
			if (accumulator > target) {
				return i;
			}
		}
		return 0;
	}
}
