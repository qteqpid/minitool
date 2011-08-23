package minitool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * 
 * 使用方法：
 * 
 * <pre>
 * for (File file : new FileWalker(&quot;.&quot;)) {
 * 	System.out.println(file);
 * }
 * </pre>
 * 
 * 或者
 * 
 * <pre>
 * new FileWalker(".").walk(new FileHandler() {
 * 	<code>@Override<code>
 * 	public void handle(File file) {
 * 		System.out.println(file);
 * 	}
 * });
 * </pre>
 * 
 * @author wing
 */
public class FileWalker implements Iterable<File> {

	private File file;

	public FileWalker(File file) throws FileNotFoundException {
		if (!file.exists()) {
			throw new FileNotFoundException(file.toString());
		}
		this.file = file;
	}

	public FileWalker(String file) throws FileNotFoundException {
		this(new File(file));
	}

	public interface FileHandler {
		public void handle(File file);
	}

	/**
	 * 使用深度优先方法遍历FileWalker中的文件，并对每个文件调用fileHandler进行处理。
	 * 
	 * @param fileHandler
	 */
	public void walk(FileHandler fileHandler) {
		walk(file, fileHandler);
	}

	private void walk(File parent, FileHandler fileHandler) {
		if (parent.isDirectory()) {
			for (File child : parent.listFiles()) {
				walk(child, fileHandler);
			}
		} else {
			fileHandler.handle(parent);
		}
	}

	@Override
	public Iterator<File> iterator() {
		return new FileIterator();
	}

	private class FileIterator implements Iterator<File> {

		class WalkState {
			private WalkState(File[] currentFiles, int currentIndex) {
				this.currentFiles = currentFiles;
				this.currentIndex = currentIndex;
			}

			private File[] currentFiles;
			private int currentIndex;

			private boolean isEnd() {
				return currentIndex >= currentFiles.length;
			}
		}

		Deque<WalkState> stateStack;

		public FileIterator() {
			stateStack = new ArrayDeque<WalkState>();
			if (file.isDirectory()) {
				stateStack.push(new WalkState(file.listFiles(), 0));
			} else {
				stateStack.push(new WalkState(new File[] { file }, 0));
			}
		}

		private File nextFile;

		@Override
		public boolean hasNext() {
			WalkState walkState = null;
			do {
				if (stateStack.isEmpty()) {
					return false;
				}
				walkState = stateStack.poll();
			} while (walkState.isEnd());

			nextFile = walkState.currentFiles[walkState.currentIndex++];
			stateStack.push(walkState);
			if (nextFile.isDirectory()) {
				stateStack.push(new WalkState(nextFile.listFiles(), 0));
				return hasNext();
			} else {
				return true;
			}
		}

		@Override
		public File next() {
			return nextFile;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Only support to traversal the dir");
		}

	}

}
