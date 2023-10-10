package dao;

import entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Dao<KEY extends Serializable, ELEMENT extends BaseEntity<KEY>>{
    ELEMENT save(ELEMENT entity);
    void delete(KEY id);
    void update(ELEMENT element);
    Optional<ELEMENT> findById(KEY id);
    List<ELEMENT> findAll();
}
