package service;
import hibernateUtils.HibernateUtil;

import entity.Client;
import org.hibernate.*;

import java.util.List;

public class ClientCrudService {

    private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public void createClient(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Client client = new Client();
        client.setName(name);

        session.persist(client);
        transaction.commit();
        transaction.rollback();
        session.close();
    }

    public Client getClientById(long id) {
        Session session = sessionFactory.openSession();
        Client client = session.get(Client.class,id);
        session.close();

        return client;
    }

    public void updateClient(long id, String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Client client = session.get(Client.class, id);
        client.setName(name);

        session.persist(client);
        transaction.commit();
        transaction.rollback();
        session.close();
    }

    public void deleteClientById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Client client = session.get(Client.class, id);

        session.remove(client);
        transaction.commit();
        transaction.rollback();
        session.close();
    }

    public List<Client> getAllClients() {
        Session session = sessionFactory.openSession();
        List<Client> clients = session.createQuery("from entity.Client", Client.class).list();
        session.close();

        return clients;
    }
}