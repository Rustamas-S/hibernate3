package sda.db.hibernate.entity;

import sda.db.hibernate.entity.util.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

    @ManyToOne
    private Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + getName() + '\'' +
                ", agent=" + agent +
                '}';
    }
}
