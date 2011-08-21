package minitool;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

public class TestNetUtil {

	@Test
	public void testWget() throws MalformedURLException, IOException {
		String excepted = "这是一个中文网页";
		ByteArrayInputStream input = new ByteArrayInputStream(
				excepted.getBytes("UTF-8"));

		URLConnection mockconnection = Mockito.mock(URLConnection.class);
		Mockito.when(mockconnection.getContentType()).thenReturn(
				"text/html; charset=utf-8");
		Mockito.when(mockconnection.getInputStream()).thenReturn(input);

		Assert.assertEquals(excepted, NetUtil.wget(mockconnection, null));

		input.reset();
		Assert.assertEquals(excepted, NetUtil.wget(mockconnection, "gb2312"));
	}
}
