package minitool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

	/**
	 * @param pattern
	 *            用来匹配的模式
	 * @param input
	 *            待搜索的字符串
	 * @return 返回找到的第一个匹配的字符串，没有找到就返回null。
	 */
	public static String find(Pattern pattern, String input) {
		return find(pattern, input, 0);
	}

	/**
	 * @param pattern
	 *            用来匹配的模式
	 * @param input
	 *            待搜索的字符串
	 * @param groupId
	 *            要返回的group的id。提醒：groupId是从1开始计数的
	 * @return 返回找到的第一个匹配的字符串中的groupId对应的字符串，没有找到就返回null。
	 */
	public static String find(Pattern pattern, String input, int groupId) {
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return matcher.group(groupId);
		}
		return null;
	}

	/**
	 * @param pattern
	 *            用来匹配的模式
	 * @param input
	 *            待搜索的字符串
	 * @return 返回找到的所有匹配的字符串，没有找到就返回空数组(new String[0])。
	 */
	public static String[] findAll(Pattern pattern, String input) {
		return findAll(pattern, input, 0);
	}

	/**
	 * @param pattern
	 *            用来匹配的模式
	 * @param input
	 *            待搜索的字符串
	 * @param groupId
	 *            要返回的group的id。提醒：groupId是从1开始计数的
	 * @return 返回找到的所有匹配的字符串中的groupId对应的字符串，没有找到就返回空数组(new String[0])。
	 */
	public static String[] findAll(Pattern pattern, String input, int groupId) {
		List<String> results = new ArrayList<String>();
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			results.add(matcher.group(groupId));
		}
		return results.toArray(new String[results.size()]);
	}

}
