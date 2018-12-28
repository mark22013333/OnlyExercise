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
import idv.onlycheng.vo.Permission;
import idv.onlycheng.vo.Person;
import idv.onlycheng.vo.Role;
import idv.onlycheng.vo.Student;
import idv.onlycheng.vo.User;

public class HibernateTestDemo3 {
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
			Role r1 = new Role();
			r1.setName("command");
			Role r2 = new Role();
			r2.setName("VIP");
			Permission p1 = new Permission();
			p1.setName("read");
			Permission p2 = new Permission();
			p2.setName("add");
			Permission p3 = new Permission();
			p3.setName("delete");
			Permission p4 = new Permission();
			p4.setName("modify");
			session.save(p1);
			session.save(p2);
			session.save(p3);
			session.save(p4);
			// 設定關係
			r1.getPermissions().add(p1);
			r1.getPermissions().add(p2);
			r2.getPermissions().add(p1);
			r2.getPermissions().add(p2);
			r2.getPermissions().add(p3);
			r2.getPermissions().add(p4);
			session.save(r1);
			session.save(r2);
			tx.commit();

			// testGet()
			Role role = (Role) session.get(Role.class, 1);
			System.out.println(role.getName());
			System.out.println("====================");
			for (Permission p : role.getPermissions()) {
				System.out.println(p.getName());
			}
			System.out.println("====================");
			Permission p = (Permission) session.get(Permission.class, 1);
			System.out.println(p.getName());
			System.out.println("-------------");
			for (Role r : p.getRoles()) {
				System.out.println(r.getName());
			}

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
