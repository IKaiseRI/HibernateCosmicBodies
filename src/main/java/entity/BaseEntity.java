package entity;

import java.io.Serializable;

public interface BaseEntity<KEY extends Serializable> {
    void setId(KEY id);

    KEY getId();
}
