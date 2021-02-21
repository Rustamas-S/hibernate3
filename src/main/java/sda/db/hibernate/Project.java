package sda.db.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sda.db.hibernate.entity.Agent;
import sda.db.hibernate.entity.Album;
import sda.db.hibernate.entity.Author;
import sda.db.hibernate.entity.Song;
import sda.db.hibernate.entity.util.AgentId;
import sda.db.hibernate.repository.AgentRepository;
import sda.db.hibernate.repository.AlbumRepository;
import sda.db.hibernate.repository.AuthorRepository;
import sda.db.hibernate.repository.SongRepository;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.List;

public class Project {

    private final AgentRepository agentRepository;
    private final AlbumRepository albumRepository;
    private final AuthorRepository authorRepository;
    private final SongRepository songRepository;

    public Project() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Agent.class)
                .addAnnotatedClass(Album.class)
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Song.class)
                .buildSessionFactory();

        EntityManager entityManager = sessionFactory.createEntityManager();

        agentRepository = new AgentRepository(entityManager);
        albumRepository = new AlbumRepository(entityManager);
        authorRepository = new AuthorRepository(entityManager);
        songRepository = new SongRepository(entityManager);
    }

    public void run() {
        Agent agent = new Agent();
        agent.setId(new AgentId("Vardenis", "Pavardenis"));
        agent.setActiveSince(Instant.now());
        agentRepository.save(agent);

        Author authorA = new Author();
        authorA.setName("Super Author");
        authorA.setAgent(agent);
        authorA.setSalary(1500.00);
        authorRepository.save(authorA);

        Author authorB = new Author();
        authorB.setName("Other Author");
        authorB.setAgent(agent);
        authorB.setSalary(1234.56);
        authorRepository.save(authorB);

        Song songD = new Song("song D", authorA, 123, Instant.now());
        songRepository.save(songD);

        Album albumA = createAlbumA(authorA);
        albumA.addSong(songD);
        albumRepository.save(albumA);

        Album albumB = createAlbumB(authorB);
        albumB.addSong(songD);
        albumRepository.save(albumB);

        print();
    }

    private Album createAlbumA(Author author) {
        Song songA = new Song("song A", author, 70, Instant.now());
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
        Song songC = new Song("song C", author, 123, Instant.now());

        Album album = new Album();
        album.setName("New Album");
        album.setAuthor(author);
        album.addSong(songC);

        return album;
    }

    private void print() {
        System.out.println("=========");
        List<Song> songs = songRepository.findAll();
        songs.forEach(System.out::println);
        System.out.println("=========");
        Song songA = songs.stream().findFirst().get();
        songA.setLyrics("some other lyrics");
        songRepository.save(songA);
        System.out.println(songRepository.find(songA.getId()));
        songRepository.delete(songA);
        System.out.println(songRepository.find(songA.getId()));
        System.out.println("=========");
        agentRepository.findAll().forEach(System.out::println);
        System.out.println("=========");
        System.out.println(agentRepository.find("Vardenis", "Pavardenis"));
        System.out.println("=========");
        songRepository.findLongerThan(50).forEach(System.out::println);
        System.out.println("=========");
        System.out.println(songRepository.findLongerThan(150));
        System.out.println("=========");
        System.out.println(agentRepository.getTotalAuthorSalaryForAgent("Vardenis", "Pavardenis"));
    }
}
