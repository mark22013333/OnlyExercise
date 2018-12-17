package hibernate01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import idv.onlycheng.vo.User;

public class HibernateTest {

	public static void main(String[] args) {

		Configuration cfg = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder()
				.applySettings(cfg.getProperties())
				.build();
		SessionFactory factory = cfg.buildSessionFactory(registry);
		Session session = factory.openSession();
		User user = (User) session.get(User.class, 1);
		
		System.out.println(user);
		session.close();
		factory.close();
	}
}
