package test.idv.cheng.demo;

import org.apache.commons.lang3.SystemUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.spi.EvictEvent;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import idv.onlycheng.Util.HibernateUtil;
import idv.onlycheng.vo.Card;
import idv.onlycheng.vo.Cluss;
import idv.onlycheng.vo.Person;
import idv.onlycheng.vo.Student;
import idv.onlycheng.vo.User;

public class HibernateTestDemo2 {
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
	public void testGet() {

	}

	@Test
	public void testInit() {
		try {
			Card c1 = new Card();
			c1.setAddress("台北");
			Card c2 = new Card();
			c2.setAddress("台中");
			Person p1 = new Person();
			p1.setName("小李");
			p1.setSex("Male");
			// 設定外鍵關連
			p1.setCard(c1);
			Person p2 = new Person();
			p2.setName("小張");
			p2.setSex("Male");
			p2.setCard(c2);
			session.save(c1);
			session.save(c2);
			session.save(p1);
			session.save(p2);
			tx.commit();

			// testGet()
			Person person = (Person) session.get(Person.class, 1);
			System.out.println(person.getName() + "\t" + person.getSex());
			System.out.println("====================");
			System.out.println(person.getCard().getAddress());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(person.getCard().getPerson());
			System.out.println(person);
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
