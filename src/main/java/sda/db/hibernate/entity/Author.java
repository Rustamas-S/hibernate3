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

    private Double salary;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + getName() + '\'' +
                ", agent=" + agent +
                ", salary=" + salary +
                '}';
    }
}
