import entity.*;
import org.flywaydb.core.Flyway;

import hibernateUtils.HibernateUtil;
import service.PlanetCrudService;
import service.TicketCrudService;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Demo {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("hibernate");
    private static final String JDBC_URL = "hibernate.connection.url";

    private static final PlanetCrudService planetCrudService = new PlanetCrudService();
    private static final TicketCrudService ticketCrudService = new TicketCrudService();

    public static void main(String[] args) {
        Flyway.configure()
                .dataSource(resourceBundle.getString(JDBC_URL), null, null)
                .load()
                .migrate();

        ticketCrudServiceCheck();
        HibernateUtil.getInstance().close();
    }

    private static void ticketCrudServiceCheck() {
        Client newClient = new Client();
        Ticket newTicket = new Ticket();

        newTicket.setCreatedAt(String.valueOf(LocalDateTime.now()));
        newClient.setId(6);
        newTicket.setClient(newClient);
        newTicket.setFromPlanet(planetCrudService.getById("FIR"));
        newTicket.setToPlanet(planetCrudService.getById("SEC"));

        ticketCrudService.create(newTicket);
        System.out.println("New ticket: " + newTicket);

        System.out.println("Get ticket by id: " + ticketCrudService.getById(11));

        Ticket updateTicketById = ticketCrudService.getById(11);
        updateTicketById.setToPlanet(planetCrudService.getById("THI"));
        ticketCrudService.update(updateTicketById);
        System.out.println("Updated ticket: " + ticketCrudService.getById(11));

        ticketCrudService.delete(5);

        System.out.println(ticketCrudService.getAll());
    }
}
