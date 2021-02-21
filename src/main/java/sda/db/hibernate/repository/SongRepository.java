package sda.db.hibernate.repository;

import sda.db.hibernate.entity.Song;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class SongRepository extends AbstractRepository<Song, UUID> {

    public SongRepository(EntityManager entityManager) {
        super(entityManager, Song.class);
    }

    @Override
    public List<Song> findAll() {
        return entityManager.createQuery("FROM Song", Song.class).getResultList();
    }

    public List<Song> findLongerThan(Integer seconds) {
        TypedQuery<Song> query = entityManager.createQuery("FROM Song WHERE duration > ?1", Song.class);
        query.setParameter(1, seconds);
        return query.getResultList();
    }
}
