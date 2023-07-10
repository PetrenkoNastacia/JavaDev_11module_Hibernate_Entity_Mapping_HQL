package service;

import hibernateUtils.HibernateUtil;
import entity.Planet;

import org.hibernate.*;

import java.util.List;

public class PlanetCrudService {

    private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public void createPlanet(String id, String name) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Planet planet = new Planet();
                planet.setId(id);
                planet.setName(name);

                session.persist(planet);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    public Planet getPlanetById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Planet.class, id);
        }
    }

    public void updatePlanet(String id, String name) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Planet planet = session.get(Planet.class, id);
                planet.setName(name);

                session.persist(planet);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    public void deletePlanetById(String id) {
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

    public List<Planet> getAllPlanets() {
        try (Session session = sessionFactory.openSession()) {
            List<Planet> planets = session.createQuery("from Planet", Planet.class).list();
            session.close();

            return planets;
        }
    }
}