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

public class HibernateTestDemo6 {
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
			ResultPK pk = new ResultPK();
			pk.setStuId(1);
			pk.setSubId(1);

			Result r = new Result();
//			r.setId(pk);
			r.setScore(85);
			session.save(r);
			tx.commit();
			// testGet()
			ResultPK pk1 = new ResultPK();
			pk1.setStuId(1);
			pk1.setSubId(1);
			Result result = (Result) session.get(Result.class, pk);
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
