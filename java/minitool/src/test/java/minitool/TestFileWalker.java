package minitool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import junit.framework.Assert;

import minitool.FileWalker.FileHandler;

import org.junit.Test;

public class TestFileWalker {

	@Test
	public void testIterator() throws FileNotFoundException {
		// TODO 暂时简单地互相测试
		final Iterator<File> iter = new FileWalker(".").iterator();
		new FileWalker(".").walk(new FileHandler() {

			@Override
			public void handle(File file) {
				Assert.assertTrue(iter.hasNext());
				Assert.assertEquals(file, iter.next());
			}
		});
		Assert.assertFalse(iter.hasNext());
	}
}
