package chillchip.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static final SessionFactory sessionFactory = createSessionFactory();
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	private static SessionFactory createSessionFactory() {
		try {
			registry = new StandardServiceRegistryBuilder()
					.configure()
					.build();

			SessionFactory sessionFactory = new MetadataSources(registry)
					.addAnnotatedClass(chillchip.trip.model.TripVO.class) // 註冊 TripVO
	                .addAnnotatedClass(chilltrip.tripactype.model.TripactypeVO.class)
	                .addAnnotatedClass(chilltrip.tripactyperela.model.TripactyperelaVO.class)
					.buildMetadata()
					.buildSessionFactory();

			return sessionFactory;
		}catch (Exception e ) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static void shutdown() {
		if(registry != null)
			StandardServiceRegistryBuilder.destroy(registry);
	}
	
	
}
