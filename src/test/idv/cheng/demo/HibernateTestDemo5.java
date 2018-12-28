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
import idv.onlycheng.vo.Role;
import idv.onlycheng.vo.Student;
import idv.onlycheng.vo.User;

public class HibernateTestDemo5 {
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
			Result r = new Result();
//			r.setStuId(1);
//			r.setSubId(1);
			r.setScore(80);
			session.save(r);
			tx.commit();
			// testGet()
			// 設定ID
			Result r1 = new Result();
//			r1.setStuId(1);
//			r1.setSubId(1);
			Result result = (Result) session.get(Result.class, r);
			System.out.println(result.getScore());

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
