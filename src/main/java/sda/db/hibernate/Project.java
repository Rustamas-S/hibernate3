package sda.db.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sda.db.hibernate.entity.Agent;
import sda.db.hibernate.entity.Album;
import sda.db.hibernate.entity.Author;
import sda.db.hibernate.entity.Song;
import sda.db.hibernate.entity.util.AgentId;
import sda.db.hibernate.repository.SongRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.Instant;
import java.util.List;

public class Project {

    public void run() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Agent.class)
                .addAnnotatedClass(Album.class)
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Song.class)
                .buildSessionFactory();

        EntityManager em = sessionFactory.createEntityManager();
        SongRepository songRepository = new SongRepository(em);

        EntityTransaction t = em.getTransaction();

        t.begin();

        Author author = new Author();
        author.setName("Super Author");

        Song songD = new Song("song D", author, 123, Instant.now());

        Album albumA = createAlbumA(author);
        albumA.addSong(songD);

        Album albumB = createAlbumB(author);
        albumB.addSong(songD);

        Agent agent = new Agent();
        agent.setId(new AgentId("Vardenis", "Pavardenis"));
        agent.setActiveSince(Instant.now());
        author.setAgent(agent);

        em.persist(agent);
        em.persist(author);
        em.persist(albumA);
        em.persist(albumB);

        t.commit();

        songRepository.findAll().forEach(System.out::println);

        List<Album> albums = em.createQuery("FROM Album", Album.class).getResultList();
        albums.forEach(System.out::println);
    }

    private Album createAlbumA(Author author) {
        Song songA = new Song("song A", author, 123, Instant.now());
        songA.setLyrics("some test lyrics");

        Song songB = new Song("song B", author, 123, Instant.now());

        Album album = new Album();
        album.setName("Old Album");
        album.setAuthor(author);
        album.addSong(songA);
        album.addSong(songB);

        return album;
    }

    private Album createAlbumB(Author author) {
        Song songA = new Song("song C", author, 123, Instant.now());

        Album album = new Album();
        album.setName("New Album");
        album.setAuthor(author);
        album.addSong(songA);

        return album;
    }
}
