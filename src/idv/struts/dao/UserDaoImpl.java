package idv.struts.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import idv.onlycheng.Util.HibernateUtil;
import idv.onlycheng.vo.User;

public class UserDaoImpl implements UserDao {

	@Override
	public List<User> list() {
		try {
			Session session = HibernateUtil.getSession();
			List<User> list = session.createCriteria(User.class).list();
			HibernateUtil.close();
			return list;
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
