package service;
import hibernateUtils.HibernateUtil;

import entity.Client;
import org.hibernate.*;

import java.util.List;

public class ClientCrudService {

    private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public void create(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            session.close();
        }
    }

    public Client getById(long id) {
        Session session = sessionFactory.openSession();
        Client client = session.get(Client.class,id);
        session.close();

        return client;
    }

    public void update(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                session.close();
            }
        }
    }

    public void deleteById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Client client = session.get(Client.class, id);

            session.remove(client);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
        }
    }

    public List<Client> getAll() {
        Session session = sessionFactory.openSession();
        List<Client> clients = session.createQuery("from entity.Client", Client.class).list();
        session.close();

        return clients;
    }
}