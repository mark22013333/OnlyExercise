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
import idv.onlycheng.vo.Role;
import idv.onlycheng.vo.Student;
import idv.onlycheng.vo.User;

public class HibernateTestDemo4 {
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
			Links link = new Links();
			link.setPhone("0912345678");
			link.setQq("235134");
			link.setEmail("abc@gmail.com");
			Employee e = new Employee();
			e.setName("ABBA");
			e.setAge(19);
			e.setLinks(link);
			session.save(e);
			tx.commit();

			// testGet()
			Employee e1 = (Employee) session.get(Employee.class, 1);
			System.out.println(e1.getName() + "\t" + e.getAge());
			System.out.println(e.getLinks().getPhone() + "\t" + e.getLinks().getEmail());

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
