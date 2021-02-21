package sda.db.hibernate.repository;

import sda.db.hibernate.entity.Author;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class AuthorRepository extends AbstractRepository<Author, UUID> {

    public AuthorRepository(EntityManager entityManager) {
        super(entityManager, Author.class);
    }

    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("FROM Author", Author.class).getResultList();
    }
}
