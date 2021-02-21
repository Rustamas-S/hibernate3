package sda.db.hibernate.repository;

import sda.db.hibernate.entity.Song;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class SongRepository extends AbstractRepository<Song, UUID> {

    public SongRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Song find(UUID uuid) {
        return entityManager.find(Song.class, uuid);
    }

    @Override
    public List<Song> findAll() {
        return entityManager.createQuery("FROM Song", Song.class).getResultList();
    }
}
