package test.idv.cheng.demo;

import java.util.Iterator;
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
public class HibernateTestDemo14 {
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

	@After
	public void testDown() {
		HibernateUtil.close();
		System.out.println("===After");
	}
}
