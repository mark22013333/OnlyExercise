package idv.onlycheng.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static Configuration cfg = null;
	private static ServiceRegistry registry = null;
	private static SessionFactory factory = null;
	private static ThreadLocal<Session> sessionLocal = null;

	static {
		cfg = new Configuration().configure();
		registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();

		// sessionFactory是一個重量級的物件 在一個應用只需要一個即可
		// 使用後也不關閉
		factory = cfg.buildSessionFactory(registry);
		sessionLocal = new ThreadLocal<Session>();
	}

	public static Session getSession() {
		if (sessionLocal.get() != null && !sessionLocal.get().isOpen()) {
			sessionLocal.set(null);
		}
		if (sessionLocal.get() == null) {
			sessionLocal.set(factory.openSession());
		}
		return sessionLocal.get();
	}

	public static void close() {
		if (sessionLocal.get() != null) {
			sessionLocal.get().close();
			sessionLocal.set(null);
		}
	}

}
