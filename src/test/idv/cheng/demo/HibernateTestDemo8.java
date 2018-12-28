package test.idv.cheng.demo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import idv.onlycheng.Util.HibernateUtil;
import idv.onlycheng.vo.Cluss;
import idv.onlycheng.vo.Student;

public class HibernateTestDemo8 {
	Session session = null;
	Transaction tx = null;

	@Before
	public void setUp() {
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
		System.out.println("===Before");
	}

	@Test
	public void testCreateDB() {
		Configuration cfg = new Configuration().configure();
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}

	@Test
	public void testInit() {
		try {

			Cluss c1 = new Cluss();
			c1.setName("103");
			c1.setAddress("A-1");
			Cluss c2 = new Cluss();
			c2.setName("102");
			c2.setAddress("A-2");

			session.save(c1);
			session.save(c2);
			
			Student s1 = new Student();
			s1.setName("John");
			s1.setAge(22);
			s1.setSex("male");
			s1.setCluss(c1);
			Student s2 = new Student();
			s2.setName("Mark");
			s2.setAge(30);
			s2.setSex("male");
			s2.setCluss(c2);
			Student s3 = new Student();
			s3.setName("Anna");
			s3.setAge(28);
			s3.setSex("female");
			s3.setCluss(c1);
			// 關連關係由"一"的那一端控制
			c1.getStudents().add(s1);
			c1.getStudents().add(s3);
			c2.getStudents().add(s2);
		
			session.save(c1);
			session.save(c2);
			session.save(s1);
			session.save(s2);
			session.save(s3);

			tx.commit();

			// testGet()
			Cluss ctCluss = (Cluss) session.get(Cluss.class, 1);
			System.out.println(ctCluss.getName() + "\t" + ctCluss.getAddress());
			System.out.println("===================");
			for (Student student : ctCluss.getStudents()) {
				System.out.println(student.getName() + "\t" + student.getAge());
			}

//			Student student = (Student) session.get(Student.class, 2);
//			session.delete(student);
//			tx.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	@Test
	public void testDelete() {
		
	}

	@After
	public void testDown() {
		HibernateUtil.close();
		System.out.println("===After");
	}
}
