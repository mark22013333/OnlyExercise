package test.idv.cheng.demo;

import java.math.BigInteger;
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
public class HibernateTestDemo12 {
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

	@Test
	public void test02Get() {
		// get方法是即時讀取資料庫，讀取資料時，如果不存在則返回『null』
		User user = (User) session.get(User.class, 1);
		System.out.println(user.getName());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test03List() {
		// 這也是即時載入，需要立即使用資料時可以使用，回應速度較快
		List<User> users = session.createQuery("from User").list();
		System.out.println("================");
		System.out.println(users.size());
	}

	// 延遲載入Lazy--可以提高系統使用率，
	// 在調用方法時不會立即在資料庫讀取數據，只有真正使用數據時才會到資料庫查詢
	// Load就是lazy
	@Test
	public void test04Load() {
		// load查詢數據，如果數據不存在將拋『異常』
		User user = (User) session.load(User.class, 1);
		System.out.println(user.getId());
		System.out.println("====＠＠============");
		System.out.println(user.getName());// 到資料庫查詢
	}

	// 關連查詢中也可以是lazy load
	@Test
	public void test05GetStudent() {
		Student stu = (Student) session.get(Student.class, 1);
		System.out.println(stu.getName() + "=-=-=-=-=" + stu.getAge());
		System.out.println("------------");
		System.out.println(stu.getCluss().getId());
		System.out.println(stu.getCluss().getName());
	}

	// Lazy="false" 採用的是即時載入，一次性將關連對象查詢出來
	// Lazy="extra" 聰明的懶載入，如果只是查詢關連對象的大小時，只會查詢個數，不會查詢數據
	@Test
	public void test06GetCluss() {
		Cluss c1 = (Cluss) session.get(Cluss.class, 1);
		System.out.println("---------------");
		System.out.println(c1.getStudents().size());
		System.out.println("===================");
		c1.getStudents();
		for (Student stu : c1.getStudents()) {
			System.out.println(stu.getName());
		}
	}

	// Class查詢對象預設是lazy="true",property預設是lazy="false";

	// 抓取策略（fetch）：指查資料時，採用什麼樣的SQL語句來查詢
	// <many-to-one name="cluss" column="cluss_id" lazy="proxy" fetch="select" />
	// select採用的是select語句查詢數據，預設採用select，在這種情況下lazy才有意義
	// join抓取，採用的是join語句查詢數據，一次將關連數據查出，lazy則沒有意義了

	@After
	public void testDown() {
		HibernateUtil.close();
		System.out.println("===After");
	}
}
