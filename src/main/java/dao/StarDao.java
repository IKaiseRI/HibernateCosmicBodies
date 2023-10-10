package dao;

import entity.star.Star;
import org.hibernate.SessionFactory;

public class StarDao extends BaseDao<Long, Star>{
    public StarDao(SessionFactory sessionFactory) {
        super(sessionFactory, Star.class);
    }
}
