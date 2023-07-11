package service;

import hibernateUtils.HibernateUtil;
import entity.Planet;

import org.hibernate.*;

import java.util.List;

public class PlanetCrudService {

    private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public void create(Planet planet) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            planet = session.get(Planet.class, planet.getId());
            Planet newPlanet = new Planet();
            try {
                newPlanet.setId(planet.getId());
                newPlanet.setName(planet.getName());
                session.persist(newPlanet);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                transaction.rollback();
                }
            }
        }
    }



    public Planet getById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Planet.class, id);
        }
    }

    public void update(Planet planet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            planet = session.get(Planet.class, planet.getId());
            try {
                planet.setName(planet.getName());
                session.persist(planet);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    public void deleteById(String id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Planet planet = session.get(Planet.class, id);
                session.remove(planet);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    public List<Planet> getAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Planet> planets = session.createQuery("from Planet", Planet.class).list();
            session.close();

            return planets;
        }
    }
}