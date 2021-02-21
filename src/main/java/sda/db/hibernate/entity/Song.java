package sda.db.hibernate.entity;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "songs")
@SecondaryTable(name = "lyrics")
public class Song {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                    .withLocale(Locale.forLanguageTag("LT"))
                    .withZone(ZoneId.systemDefault());

    @Id
    @GeneratedValue()
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Author author;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Instant releaseDate;

    @Column(table = "lyrics")
    private String lyrics;

    @ManyToMany
    private List<Album> albums = new ArrayList<>();

    public Song(String name, Author author, Integer duration, Instant releaseDate) {
        this.name = name;
        this.author = author;
        this.duration = duration;
        this.releaseDate = releaseDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getDurationString() {
        int s = duration % 60;
        return duration / 60 + ":" + (s < 10 ? "0" : "") + duration % 60;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", author=" + author +
                ", duration=" + getDurationString() +
                ", releaseDate=" + FORMATTER.format(releaseDate) +
                ", lyrics=" + lyrics +
                ", albums=" + albums.stream().map(Album::getName).collect(Collectors.toList()) +
                '}';
    }
}
