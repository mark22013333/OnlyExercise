package test.idv.cheng.demo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.spi.EvictEvent;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import idv.onlycheng.Util.HibernateUtil;
import idv.onlycheng.vo.Cluss;
import idv.onlycheng.vo.Student;
import idv.onlycheng.vo.User;

public class HibernateTestDemo {
	Session session = null;
	Transaction tx = null;

	@Before
	public void setUp() {
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
		System.out.println("===Before");
	}

	// @Test
	// public void testSave() {
	// try {
	// User user = new User();
	// user.setName("Alice");
	// user.setAge(25);
	// session.save(user);
	// tx.commit();
	// } catch (Exception e) {
	// // TODO: handle exception
	// if (tx != null) {
	// tx.rollback();
	// }
	// }
	// }

	// @Test
	// public void testSession1() {
	// User user = null;
	// try {
	// // 剛new出USER處於瞬時狀態
	// user = new User();
	// user.setName("10");
	// user.setAge(30);
	// // save後為永久狀態，持久化狀態可被session管理
	// session.save(user);
	// // 處於持久狀態時，交易錢會將session中物件和資料庫中的紀錄進行髒讀檢查
	// // 如果不一致進行資料同步
	// user.setAge(23);
	// tx.commit();
	//
	// } catch (Exception e) {
	// // TODO: handle exception
	// if (tx != null) {
	// tx.rollback();
	// }
	// }
	// // 清除指定物件---session不再管理user
	// // user處於游離狀態
	// session.evict(user);
	// // close關閉session,clear清除session中所有物件
	// }
	//
	// @Test
	// public void testSession2() {
	// User user = null;
	// try {
	// // user物件被session管理，處於持久狀態
	// user = (User) session.get(User.class, 19);
	// // 調用clear後session不再管理user，資料處於游離狀態（表示session中不存在，但資料庫中有此紀錄）
	// session.clear();
	// user.setName("yoyo");
	// // 調用update後user物件被session管理，處於持久狀態（表示session和資料庫都有此紀錄）
	// session.update(user);
	// user.setName("三年");
	// // 刪除後user物件處於瞬時狀態（表示session中不存在，資料庫也沒有此筆紀錄）
	// session.delete(user);
	// tx.commit();
	//
	// } catch (Exception e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// if (tx != null) {
	// tx.rollback();
	// }
	// }
	// }

	@Test
	public void testCreateDB() {
		Configuration cfg = new Configuration().configure();
		// 使得Hibernate映設訊息轉換為DDL
		SchemaExport se = new SchemaExport(cfg);
		// 第一個參數 是否印出DDL語句
		// 第二個參數 是否將DDL到資料庫執行
		se.create(true, true);
		System.out.println("===Create DB complete");
	}

	@Test
	public void testInit() {
		try {
			Cluss c1 = new Cluss();
			c1.setName("103");
			c1.setAddress("A-1");
			Cluss c2 = new Cluss();
			c2.setName("301");
			c2.setAddress("B-2");
			session.save(c1);
			session.save(c2);

			Student s1 = new Student();
			s1.setName("Andy");
			s1.setAge(30);
			s1.setSex("male");
			// 設定學生的外鍵，如果不設值，保存的學生將沒有外鍵值
			s1.setCluss(c1);

			Student s2 = new Student();
			s2.setName("Mark");
			s2.setAge(31);
			s2.setSex("male");
			s2.setCluss(c2);

			Student s3 = new Student();
			s3.setName("coco");
			s3.setAge(28);
			s3.setSex("female");
			s3.setCluss(c1);

			session.save(s1);
			session.save(s2);
			session.save(s3);
			tx.commit();
			System.out.println("===commit");

			// 關連映設主要為查詢服務，當具有多對一的關係時，在獲取"多"的一端的訊息可以直接獲取"一"的那端的訊息
			Student student = (Student) session.get(Student.class, 1);
			System.out.println(student.getName() + "-------" + student.getAge());
			System.out.println("===========================");
			System.out.println(student.getCluss().getName() + "----------" + student.getCluss().getAddress());

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
				System.out.println("===rollback");
			}
		}
	}

	// @Test
	// public void testGet() {
	// Student student = (Student) session.get(Student.class, 1);
	// System.out.println(student.getName() + "-------" + student.getAge());
	// System.out.println("===========================");
	// System.out.println(student.getCluss().getName() + "----------" +
	// student.getCluss().getAddress());
	// }

	// @Test
	// public void testSave() {
	//
	// try {
	// User user = new User();
	// user.setName("測一波");
	// user.setAge(12);
	// session.save(user);
	// tx.commit();
	//
	// } catch (Exception e) {
	// if (tx != null) {
	// tx.rollback();
	// }
	// }
	// }

	@After
	public void testDown() {
		HibernateUtil.close();
		System.out.println("===After");
	}
}
