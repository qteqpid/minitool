package minitool;

public class MathUtil {

	public static final int[] EMPTY_INT_ARRAY = new int[0];

	/**
	 * 求和
	 * 
	 * @param nums
	 *            待求和的数据
	 * @return nums的和
	 */
	public static int sum(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		return sum;
	}

	/**
	 * 按照从小到大的顺序返回[begin, end)区间的所有整数
	 * 
	 * @param begin
	 * @param end
	 * @return [begin, end)区间的所有整数构成的数组。当end<=begin时，返回一个大小为0的数组(new int[0])。
	 */
	public static int[] range(int begin, int end) {
		if (end <= begin) {
			return EMPTY_INT_ARRAY;
		}

		int[] array = new int[end - begin];
		for (int i = 0; i < array.length; i++) {
			array[i] = begin + i;
		}
		return array;
	}
}
