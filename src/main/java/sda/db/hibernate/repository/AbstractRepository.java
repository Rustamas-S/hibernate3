package sda.db.hibernate.repository;

import javax.persistence.EntityManager;

public abstract class AbstractRepository<T, ID> implements Repository<T, ID> {

    protected EntityManager entityManager;

    public AbstractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
