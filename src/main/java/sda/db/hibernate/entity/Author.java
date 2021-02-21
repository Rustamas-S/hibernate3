package sda.db.hibernate.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne
    private Agent agent;

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

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", agent=" + agent +
                '}';
    }
}
