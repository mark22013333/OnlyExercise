package test.idv.cheng.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
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

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class HibernateTestDemo9 {
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

			// Cluss cluss = new Cluss();
			// cluss.setName("208");
			// cluss.setAddress("A-1");
			// Cluss cluss2 = new Cluss();
			// cluss.setName("103");
			// cluss.setAddress("B-2");

			// Student student = new Student();
			// student.setName("AAA");
			// student.setAge(12);
			// student.setSex("male");
			// student.setCluss(cluss);
			// Student student2 = new Student();
			// student2.setName("BBB");
			// student2.setAge(15);
			// student.setSex("male");
			// student2.setCluss(cluss2);
			// Student student3 = new Student();
			// student3.setName("CCC");
			// student3.setAge(22);
			// student.setSex("female");
			// student3.setCluss(cluss);

			// session.save(cluss);
			// session.save(cluss2);

			// session.save(student);
			// session.save(student2);
			// session.save(student3);

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
	public void test02FindAll() {
		try {
			// 編寫HQL語句，在HQL中如果使用了select *，可以省略
			String hql = "from User";
			// Query query = session.createQuery(hql);
			// List<User> users = query.list();

			List<User> users = session.createQuery(hql).list();
			for (User user : users) {
				System.out.println("@==1==@" + user.getName() + "\t" + user.getAge());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test03Condition() {
		String hql = "from User where age>25";
		List<User> users = session.createQuery(hql).list();
		for (User user : users) {
			System.out.println("@==2==@" + user.getName() + "\t" + user.getAge());
		}
	}

	@Test
	public void test04condition2() {
		String hql = "from User where age>?";
		List<User> users = session.createQuery(hql).setInteger(0, 25).list();
		for (User user : users) {
			System.out.println("@==3==@" + user.getName() + "\t" + user.getAge());
		}

	}

	@Test
	public void test05condition3() {
		// 參數的命名以冒號開頭
		String hql = "from User where age>:age";
		List<User> users = session.createQuery(hql).setInteger("age", 25).list();
		for (User user : users) {
			System.out.println("@==4==@" + user.getName() + "\t" + user.getAge());
		}
	}

	// 查詢指定行的資料
	@Test
	public void test06Column() {
		String hql = "select u.name,u.age from User u ";
		List<Object[]> users = session.createQuery(hql).list();
		for (Object[] user : users) {
			System.out.println("@==5==@" + user[0] + "\t" + user[1]);
		}
	}

	@Test
	public void test07Column2Object() {
		String hql = "select new User(u.name,u.age) from User u";
		List<User> users = session.createQuery(hql).list();
		for (User user : users) {
			System.out.println("@==6==@" + user.getName() + "\t" + user.getAge());
		}
	}

	// 模糊查詢
	@Test
	public void test08Like() {
		String hql = "from User u where u.name like ?";
		List<User> users = session.createQuery(hql).setString(0, "%一%").list();
		for (User user : users) {
			System.out.println("@==7==@" + user.getName() + "\t" + user.getAge());
		}
	}

	// unique Result 唯一結果，結果只有一個或者為null
	@Test
	public void test09Unique() {
		String hql = "from User u where u.name like ?";
		User user = (User) session.createQuery(hql).setString(0, "%一%").uniqueResult();
		System.out.println("@==8==@" + user.getName() + "\t" + user.getAge());
	}

	// 聚合函數查詢
	@Test
	public void test10Count() {
		String hql = "select count(u.id) from User u";
		Long count = (Long) session.createQuery(hql).uniqueResult();
		System.out.println("@==9==@" + count);
	}

	// 分頁
	@Test
	public void test11Page() {
		String hql = "from User";
		@SuppressWarnings("unchecked")
		List<User> users = session.createQuery(hql)
				// (currentPage-1)*pageSize
				.setFirstResult(0)
				// pageSize
				.setMaxResults(2).list();
		for (User user : users) {
			System.out.println("@==10==@" + user.getName() + "\t" + user.getAge());
		}
	}

	// 排序
	@Test
	public void test12Order() {
		String hql = "from User u order by u.age asc";
		List<User> users = session.createQuery(hql).list();
		for (User user : users) {
			System.out.println("@==11==@" + user.getName() + "\t" + user.getAge());
		}
	}

	// in 可以指定找出資料
	@Test
	public void test13In() {
		String hql = "from User u where u.id in(:ids)";
		Object[] ids = { 2, 3, 5 };
		List<User> users = session.createQuery(hql).setParameterList("ids", ids).list();
		for (User user : users) {
			System.out.println(user.getName() + "\t" + user.getAge());
		}
	}

	// 連接查詢
	@Test
	public void test14Join() {
		String hql = "from Student s where s.cluss.name=?";
		List<Student> students = session.createQuery(hql).setString(0, "103").list();
		for (Student student : students) {
			System.out.println(student.getName() + "\t" + student.getAge());
		}
	}

	@After
	public void testDown() {
		HibernateUtil.close();
		System.out.println("===After");
	}
}
