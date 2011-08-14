package minitool;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class NetUtil {

	/**
	 * 从指定的url下载页面并以字符串的形式返回。
	 * <p>
	 * 页面的字符编码由http头的content-type来指定，如果http头没有content-type字段，则使用系统的默认字符编码
	 * 
	 * @param url
	 *            需要下载的url
	 * @return 页面的内容
	 * @throws IOException
	 */
	public static String wget(URL url) throws IOException {
		return wget(url, null);
	}

	/**
	 * 从指定的url下载页面并以字符串的形式返回。
	 * <p>
	 * 页面的字符编码由http头的content-type来指定，如果http头没有content-type字段，则使用encoding
	 * 
	 * @param url
	 *            需要下载的url
	 * @param encoding
	 *            页面的字符编码
	 * @return 页面的内容
	 * @throws IOException
	 */
	public static String wget(URL url, String encoding) throws IOException {
		URLConnection connect = url.openConnection();
		connect.connect();
		InputStream input = connect.getInputStream();

		try {
			// 优先使用content-type指定的字符编码
			String contentType = connect.getContentType();
			if (contentType != null) {
				String temp = getCharsetFromContentType(contentType
						.toLowerCase());
				if (StringUtils.isNotEmpty(temp)) {
					encoding = temp;
				}
			}

			if (encoding == null) {
				return IOUtils.toString(input);
			} else {
				return IOUtils.toString(input, encoding);
			}
		} finally {
			input.close();
		}
	}

	/**
	 * 从http头的content-type中提取charset。如果解析失败则返回null
	 * 
	 * @param contentType
	 *            http头的content-type的内容
	 * @return 检测出来的charset，如果解析失败则返回null
	 */
	public static String getCharsetFromContentType(String contentType) {
		return RegexUtil.find(CHARSET_PATTERN, contentType, 1);
	}

	private static Pattern CHARSET_PATTERN = Pattern.compile(
			"charset=['\"]?(.*?)['\"]?", Pattern.CASE_INSENSITIVE);
}
