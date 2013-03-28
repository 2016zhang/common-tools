package com.baijob.commonTools;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class IoUtil {
	
	private static int ioBufferSize = 16;
	
	/**
	 * 将Reader中的内容复制到Writer中
	 */
	public static int copy(Reader input, Writer output) throws IOException {
		char[] buffer = new char[ioBufferSize];
		int count = 0;
		int readSize;
		while ((readSize = input.read(buffer, 0, ioBufferSize)) >= 0) {
			output.write(buffer, 0, readSize);
			count += readSize;
		}
		output.flush();
		return count;
	}
}
