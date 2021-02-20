package sda.db.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Project {

    public void run() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }
}
