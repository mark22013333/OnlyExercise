package cheng.exercise07;

import java.io.File;
import java.util.ArrayList;

public class TestFile {

	public static void main(String[] args) {

		try {
			File file = new File("/usr/local");
			if (file.isFile()) {
				System.out.println(file.canRead() ? "可讀" : "不可讀");
			} else {
				File[] files = file.listFiles();
				ArrayList<File> fileList = new ArrayList<>();
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						System.out.println("[" + files[i].getPath() + "]");
					}
					else {
						fileList.add(files[i]);
					}
				}
				
				for (File f : fileList) {
					System.out.println(f.toString());
				}
				System.out.println();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("using:java FileDemo Pathname");
		}

	}

}
