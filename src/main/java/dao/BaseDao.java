package dao;

import entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseDao<KEY extends Serializable, ELEMENT extends BaseEntity<KEY>> implements Dao<KEY, ELEMENT> {
    private final SessionFactory sessionFactory;
    private final Class<ELEMENT> clazz;

    @Override
    public ELEMENT save(ELEMENT entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);

        return entity;
    }

    @Override
    public void delete(KEY id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(clazz, id));
        session.flush();
    }

    @Override
    public void update(ELEMENT element) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(element);
    }

    @Override
    public Optional<ELEMENT> findById(KEY id) {
        Session session = sessionFactory.getCurrentSession();

        return Optional.ofNullable(session.find(clazz, id));
    }

    @Override
    public List<ELEMENT> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<ELEMENT> criteriaQuery = session.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.from(clazz);

        return session.createQuery(criteriaQuery)
                .getResultList();
    }
}
