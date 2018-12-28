package test.idv.cheng.demo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import idv.onlycheng.Util.HibernateUtil;
import idv.onlycheng.vo.Card;
import idv.onlycheng.vo.Subject;
import idv.onlycheng.vo.Employee;
import idv.onlycheng.vo.Links;
import idv.onlycheng.vo.Permission;
import idv.onlycheng.vo.Person;
import idv.onlycheng.vo.Result;
import idv.onlycheng.vo.ResultPK;
import idv.onlycheng.vo.Role;
import idv.onlycheng.vo.Student;
import idv.onlycheng.vo.User;

public class HibernateTestDemo7 {
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

			Student stu = (Student) session.get(Student.class, 1);
			Subject sub = (Subject) session.get(Subject.class, 1);
			Result result = new Result();
			result.setStudent(stu);
			result.setSubject(sub);
			result = (Result) session.get(Result.class, result);
			System.out.println(result.getScore());
			result.setScore(90);
			session.save(result);
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
