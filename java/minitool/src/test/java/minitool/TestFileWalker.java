package minitool;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

public class TestFileWalker {

	@Test
	public void testIterator() throws FileNotFoundException {
		// 要如何测试呢？

		for (File f : new FileWalker("d:\\workspace\\minitool")) {
			System.out.println(f);
		}
	}
}
