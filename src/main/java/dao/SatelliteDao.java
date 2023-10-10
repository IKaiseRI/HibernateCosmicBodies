package dao;

import entity.satellite.Satellite;
import org.hibernate.SessionFactory;

public class SatelliteDao extends BaseDao<Long, Satellite>{
    public SatelliteDao(SessionFactory sessionFactory) {
        super(sessionFactory, Satellite.class);
    }
}
