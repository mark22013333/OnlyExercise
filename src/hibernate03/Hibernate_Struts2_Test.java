package hibernate03;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omg.CORBA.PRIVATE_MEMBER;

import idv.onlycheng.Util.HibernateUtil;
import idv.onlycheng.vo.User;

public class Hibernate_Struts2_Test {

	public static void main(String[] args) {
		save();
		update(6);
		delete(5);
	}

	private static void save() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			// 這兩行等於上面一行
			// Transaction tx = session.getTransaction();
			// tx.begin();
			User user = new User();
			user.setName("Bruce3");
			user.setAge(35);
			session.save(user);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			HibernateUtil.close();
		}
	}

	private static void update(int id) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, id);
			System.out.println(user.toString());
			user.setName("六六六");
			session.save(user);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			HibernateUtil.close();
		}
	}

	private static void delete(int id) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, id);
			session.delete(user);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			HibernateUtil.close();
		}
	}
}
