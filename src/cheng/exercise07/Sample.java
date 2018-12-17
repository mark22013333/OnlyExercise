package cheng.exercise07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class Sample {

	public static void main(String[] args) throws IOException {
		int ibytes = 0;
		int ichar = 0;
		int irow = 0;
		String str = "";
		File file = new File("D:\\Sample.txt");
		FileInputStream fis = new FileInputStream(file);
		while (fis.read() != -1) {
			ibytes++;
		}
		FileReader fr = new FileReader(file);
		while (fr.read() != -1) {
			ichar++;
		}
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((str = br.readLine()) != null) {
			System.out.println(str);
			irow++;
		}
		br.close();
		fr.close();
		fis.close();
		System.out.println(ibytes + "位元組\n" + ichar + "個字元\n" + irow + "列資料\n");
	}

}
