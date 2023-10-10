package dao;

import entity.planet.Planet;
import org.hibernate.SessionFactory;

public class PlanetDao extends BaseDao<Long, Planet> {

    public PlanetDao(SessionFactory sessionFactory) {
        super(sessionFactory, Planet.class);
    }
}
