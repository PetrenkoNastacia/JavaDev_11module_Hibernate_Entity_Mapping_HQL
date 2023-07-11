package service;

import entity.Client;
import entity.Planet;
import entity.Ticket;

import hibernateUtils.HibernateUtil;
import org.hibernate.*;

import java.util.List;

public class TicketCrudService {

    private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public void create(Client client, Planet fromPlanet, Planet toPlanet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Ticket ticket = new Ticket();
                ticket.setClient(client);
                ticket.setFromPlanet(fromPlanet);
                ticket.setToPlanet(toPlanet);

                session.persist(ticket);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nArguments of method create() can't be null");
        }
    }

    public Ticket getById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ticket.class, id);
        }
    }

    public void update(Client client, long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Ticket ticket = session.get(Ticket.class, id);
                ticket.setClient(client);

                session.persist(ticket);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Ticket ticket = session.get(Ticket.class, id);

                session.remove(ticket);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    public List<Ticket> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        }
    }
}