package cheng.exercise07;
	
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import org.junit.Test;

import cheng.exercise08.Train;

public class HomeWork_09_8 {

	// 第一題
	@Test
	public void testCollection() {
		List<Object> list = new ArrayList<Object>();
		list.add(new Integer(100));
		list.add(new Double(3.14));
		list.add(new Long(21L));
		list.add(new Short("100"));
		list.add(new Double(5.1));
		list.add("Kitty");
		list.add(new Integer(100));
		list.add(new Object());
		list.add("Snoopy");
		list.add(new BigInteger("1000"));

		Iterator<Object> it = list.iterator();
		while (it.hasNext()) {
			System.out.println((Object) it.next());
		}
		System.out.println("==================");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("==================");
		for (Object object : list) {
			System.out.println(object);
		}
		System.out.println("==================");
		for (int i = (list.size() - 1); i >= 0; i--) {
			if (list.get(i) instanceof Number) {
				System.out.println(list.get(i));
			}
			list.remove(i);
		}
	}
	// 第一題

	// 第二題
	@Test
	public void testTrainHashSet() {

		Train t1 = new Train(202, "普悠瑪", "樹林", "花蓮", 400);
		Train t2 = new Train(1254, "區間", "屏東", "基隆", 700);
		Train t3 = new Train(118, "自強", "高雄", "台北", 500);
		Train t4 = new Train(1288, "區間", "新竹", "基隆", 400);
		Train t5 = new Train(122, "自強", "台中", "花蓮", 600);
		Train t6 = new Train(1222, "區間", "樹林", "七堵", 300);
		Train t7 = new Train(1254, "區間", "屏東", "基隆", 700);

		Set<Train> hashSet = new HashSet<Train>();

		hashSet.add(t1);
		hashSet.add(t2);
		hashSet.add(t3);
		hashSet.add(t4);
		hashSet.add(t5);
		hashSet.add(t6);
		hashSet.add(t7); 

		Iterator<Train> it = hashSet.iterator();
		while (it.hasNext()) {
			System.out.println((Train) it.next());
		}
		System.out.println("=======================================");
		for (Iterator<Train> iterator = hashSet.iterator(); iterator.hasNext();) {
			System.out.println((Train) iterator.next());
		}
	}

	@Test
	public void testTrainTreeSet() {

		Train t1 = new Train(202, "普悠瑪", "樹林", "花蓮", 400);
		Train t2 = new Train(1254, "區間", "屏東", "基隆", 700);
		Train t3 = new Train(118, "自強", "高雄", "台北", 500);
		Train t4 = new Train(1288, "區間", "新竹", "基隆", 400);
		Train t5 = new Train(122, "自強", "台中", "花蓮", 600);
		Train t6 = new Train(1222, "區間", "樹林", "七堵", 300);
		Train t7 = new Train(1254, "區間", "屏東", "基隆", 700);

		Set<Train> treeSet = new TreeSet<Train>();

		treeSet.add(t1);
		treeSet.add(t2);
		treeSet.add(t3);
		treeSet.add(t4);
		treeSet.add(t5);
		treeSet.add(t6);
		treeSet.add(t7);

		for (Train t : treeSet) {
			System.out.println(t);
		}
	}

	@Test
	public void testTrainList() {
		Train t1 = new Train(202, "普悠瑪", "樹林", "花蓮", 400);
		Train t2 = new Train(1254, "區間", "屏東", "基隆", 700);
		Train t3 = new Train(118, "自強", "高雄", "台北", 500);
		Train t4 = new Train(1288, "區間", "新竹", "基隆", 400);
		Train t5 = new Train(122, "自強", "台中", "花蓮", 600);
		Train t6 = new Train(1222, "區間", "樹林", "七堵", 300);
		Train t7 = new Train(1254, "區間", "屏東", "基隆", 700);
		Train t8 = new Train(116, "自強", "高雄", "台北", 500);

		List<Train> arrayList = new ArrayList<Train>();
		arrayList.add(t1);
		System.out.println(arrayList.get(0));

		Set<Train> treeSet = new TreeSet<Train>();
		treeSet.add(t2);
		System.out.println(treeSet.iterator().next());

		Set<Train> hashSet = new HashSet<Train>();
		hashSet.add(t3);
		System.out.println(hashSet.iterator().next());

		Map<Integer, Train> hashMap = new HashMap<Integer, Train>();
		hashMap.put(4, t4);
		System.out.println(hashMap.get(4));

		Map<Integer, Train> treeMap = new TreeMap<Integer, Train>();
		treeMap.put(5, t5);
		System.out.println(treeMap.get(5));

		Hashtable<Integer, Train> hashTable = new Hashtable<Integer, Train>();
		hashTable.put(6, t6);
		System.out.println(hashTable.get(6));

		Vector<Train> vectorList = new Vector<Train>();
		vectorList.add(t7);
		vectorList.add(t8);
		System.out.println(vectorList.get(0) + "\n" + vectorList.get(1));
		
		Enumeration<Train> enumeration = new Enumeration<Train>() {

			@Override
			public boolean hasMoreElements() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Train nextElement() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}
	// 第二題

}
