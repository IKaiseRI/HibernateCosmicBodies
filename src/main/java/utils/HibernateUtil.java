package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.transaction.Transactional;

public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();

        return configuration.buildSessionFactory();
    }

    @Transactional
    public static void sessionRunner() {
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            //Create from Dao
            session.beginTransaction();
            Service.createCosmicBodies(sessionFactory);
            session.getTransaction().commit();
        }
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            //Read from Dao
            session.beginTransaction();
            Service.readCosmicBodies(sessionFactory);
            session.getTransaction().commit();
        }
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            //Update from dao
            session.beginTransaction();
            Service.updateCosmicBodiesConnections(sessionFactory);
            session.getTransaction().commit();
        }
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            //Read from Dao
            session.beginTransaction();
            Service.readCosmicBodies(sessionFactory);
            session.getTransaction().commit();
        }
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            //Delete from dao
            session.beginTransaction();
            Service.deleteCosmicBody(sessionFactory);
            session.getTransaction().commit();
        }
    }
}
