package minitool;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class TestNetUtil {

	@Test
	public void testWget() throws MalformedURLException, IOException {
		// 简单测试一下是否能拿到页面
		NetUtil.wget(new URL("http://www.google.com"));
		NetUtil.wget(new URL("http://www.google.com"), "UTF-8");
	}
}
