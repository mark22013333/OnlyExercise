package cheng.exercise07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;

public class HomeWork_09_7 {

	// 第一題
	public void Sample() throws IOException {

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
	// 第一題

	// 第二題
	public void Data() throws IOException {

		FileWriter fw = new FileWriter("D:\\Data.txt", true);
		BufferedWriter bfw = new BufferedWriter(fw);
		for (int i = 0; i < 10; i++) {
			bfw.write((int) ((Math.random() * 1000) + 1) + " ");
		}

		bfw.flush();
		bfw.close();
		fw.close();
	}
	// 第二題

	// 第三題
	public void Copy(String source, String Destination) throws IOException {

		Reader reader = new FileReader(source);
		Writer writer = new FileWriter(Destination);
		char[] buffer = new char[100];
		int len = 0;
		while ((len = reader.read(buffer)) != -1) {
			writer.write(buffer, 0, len);
		}

		writer.close();
		reader.close();
	}
	// 第三題

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// 第四題
		Animal[] animals = new Animal[4];
		animals[0] = new Cat("Cat1", 1, 5f);
		animals[1] = new Cat("Cat2", 2, 6f);
		animals[2] = new Dog("Dog1", 3, 7f);
		animals[3] = new Dog("Dog2", 4, 8f);

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		File dir = new File("D:\\data");
		if (!dir.exists()) {
			dir.mkdir();
		}
		try {
			oos = new ObjectOutputStream(new FileOutputStream("D:\\data\\Object.dat"));
			for (Object obj : animals) {
				oos.writeObject(obj);
			}
			ois = new ObjectInputStream(new FileInputStream("D:\\data\\Object.dat"));
			for (int i = 0; i < animals.length; i++) {
				((Animal) ois.readObject()).speak();
			}
		} catch (IOException e) {
			e.getMessage();
		} catch (ClassNotFoundException e) {
			e.getMessage();
		} finally {
			ois.close();
			oos.close();
		}
		// 第四題
	}
}
