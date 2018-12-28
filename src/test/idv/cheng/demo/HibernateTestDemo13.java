package test.idv.cheng.demo;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import idv.onlycheng.Util.HibernateUtil;
import idv.onlycheng.vo.Cluss;
import idv.onlycheng.vo.Student;
import idv.onlycheng.vo.User;
import idv.struts.service.UserService;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class HibernateTestDemo13 {
	Session session = null;
	Transaction tx = null;

	@Before
	public void setUp() {
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
		System.out.println("===Before");
	}

	@Test
	public void test00CreateDB() {
		Configuration cfg = new Configuration().configure();
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}

	@Test
	public void test01Init() {
		try {
			User user = new User();
			user.setName("Andy");
			user.setAge(30);
			User user1 = new User();
			user1.setName("測一波");
			user1.setAge(18);
			User user2 = new User();
			user2.setName("John");
			user2.setAge(20);
			User user3 = new User();
			user3.setName("anna");
			user3.setAge(35);
			User user4 = new User();
			user4.setName("Mary");
			user4.setAge(27);

			Cluss cluss = new Cluss();
			cluss.setName("208");
			cluss.setAddress("A-1");
			Cluss cluss2 = new Cluss();
			cluss2.setName("103");
			cluss2.setAddress("B-2");

			Student student = new Student();
			student.setName("AAA");
			student.setAge(12);
			student.setSex("male");
			student.setCluss(cluss);
			Student student2 = new Student();
			student2.setName("BBB");
			student2.setAge(15);
			student2.setSex("male");
			student2.setCluss(cluss2);
			Student student3 = new Student();
			student3.setName("CCC");
			student3.setAge(22);
			student3.setSex("female");
			student3.setCluss(cluss);

			session.save(cluss);
			session.save(cluss2);

			session.save(student);
			session.save(student2);
			session.save(student3);

			session.save(user);
			session.save(user1);
			session.save(user2);
			session.save(user3);
			session.save(user4);

			tx.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}
	// 快取：提高執行效率
	// Hibernate 提供了三種快取，一級快取、二級快取、查詢快取
	// 一級快取又稱為session快取，是Thread級別的快取，生命週期比較短

	// ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝以下為一級快取＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
	// Get方法獲取，先檢查快取中是否有該數據，如果有直接使用，如果沒有那麼從資料庫中查詢，並寫入快取

	@Test
	public void test02Get() {
		User user = (User) session.get(User.class, 1);
		System.out.println(user.getName());
		System.out.println("-----------");
		user = (User) session.get(User.class, 1);
		System.out.println(user.getName());
	}

	// Load方法獲取，先檢查快取中是否有該數據，如果有直接使用，如果沒有那麼從資料庫中查詢，並寫入快取
	@Test
	public void test03Load() {
		User user = (User) session.load(User.class, 1);
		System.out.println(user.getName());
		System.out.println("-----------");
		user = (User) session.load(User.class, 1);
		System.out.println(user.getName());
	}

	// List方法獲取，直接從資料庫中獲取，獲取後會將資料寫入快取
	@SuppressWarnings("unchecked")
	@Test
	public void test04List() {
		List<User> users = session.createQuery("from User").list();
		System.out.println("===============");
		User user = (User) session.get(User.class, 1);
		System.out.println(user.getName());
		System.out.println("-----------------");
		users = session.createQuery("from User").list();
	}

	// iterate方法：獲取數據先獲取數據id，如果使用數據在根據id查詢數據，是一種延遲載入方式
	// 此方法獲取數據時會先檢查快取，如果有則直接使用，如果沒有則到資料庫中查詢再寫入快取
	@SuppressWarnings("unchecked")
	@Test
	public void test05Iterate() {
		Iterator<User> users = session.createQuery("from User").iterate();
		while (users.hasNext()) {
			User u = users.next();
			System.out.println(u.getName());
		}
		System.out.println("---------------");
		Iterator<User> users2 = session.createQuery("from User").iterate();
		while (users2.hasNext()) {
			User u = users2.next();
			System.out.println(u.getName());
		}

	}

	// 1和N+1問題：1指list方法通過一條sql語句將所有數據查詢出來，而iterate方法通過N+1條sql語句
	// 將數據查詢。他們的不同在於載入數據的方式不同。

	// 快取的管理：一般情況下不用管理一級快取，如果在進行數據批量處理時可以需要管理快取。
	// 管理快取可以使用：evict清楚指定對象，clear清空快取，flush更新快取資料，close關閉快取。

	// ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝以下為二級快取＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
	// 二級快取又稱為SessionFactory快取，是Thread級別的，生命週期較長，並且可以在集群中使用。
	// 快取的數據比較多，當需要清理快取時使用相關的算法來進行清理(可能需要一部份一部份的清除)
	// 例如：LRU,FIFO等...一個是『最近最少使用』算法(Least Recently Used)，一個是先進先出
	// Hibernate二級快取採用第三方提供的套件來實現
	// 要有設定檔ehcache.xml並且要在hibernate.cfg.xml加入設定
	// 在映射文件中加入<cache>標籤<cache usage="read-only"/>
	// 測試：使用二級快取，說明在關閉session還能繼續查到資料(不會再有SQL語句)

	@Test
	public void test06Get() {
		User user = (User) session.get(User.class, 1);
		System.out.println(user.getName());
		session.close();
		System.out.println("======@@@@@@@@=======");
		session = HibernateUtil.getSession();
		user = (User) session.get(user.getClass(), 1);
		System.out.println(user.getName());
	}

	// 查詢快取：其實就是在二級快取的基礎上使用
	// 開啟查詢快取 hibernate.cfg.xml <property
	// name="cache.use_query_cache">true</property>
	// 在查詢數據時，設定查詢快取有效

	@SuppressWarnings("unchecked")
	@Test
	public void test07List() {
		List<User> users = session.createQuery("from User").setCacheable(true).list();
		System.out.println("===============");
		User user = (User) session.get(User.class, 1);
		System.out.println(user.getName());
		System.out.println("-----------------");
		users = session.createQuery("from User").setCacheable(true).list();

	}

	@After
	public void testDown() {
		HibernateUtil.close();
		System.out.println("===After");
	}
}
