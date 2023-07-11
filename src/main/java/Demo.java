import org.flywaydb.core.Flyway;

import hibernateUtils.HibernateUtil;
import service.ClientCrudService;
import service.PlanetCrudService;
import service.TicketCrudService;

import java.util.ResourceBundle;

public class Demo {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("hibernate");
    private static final String JDBC_URL = "hibernate.connection.url";

    private static final ClientCrudService clientCrudService = new ClientCrudService();
    private static final PlanetCrudService planetCrudService = new PlanetCrudService();
    private static final TicketCrudService ticketCrudService = new TicketCrudService();

    public static void main(String[] args) {
        Flyway.configure()
                .dataSource(resourceBundle.getString(JDBC_URL), null, null)
                .load()
                .migrate();
        ticketCrudServiceCheck();
        ticketCrudServiceNullCheck();

        HibernateUtil.getInstance().close();
    }

    private static void ticketCrudServiceCheck() {
        ticketCrudService.create(clientCrudService.getById(6),
                planetCrudService.getById("FIR"),
                planetCrudService.getById("THI"));

        System.out.println(ticketCrudService.getById(11));

        ticketCrudService.update(clientCrudService.getById(6), 11);
        ticketCrudService.delete(5);
        System.out.println(ticketCrudService.getAll());
    }

    private static void ticketCrudServiceNullCheck() {
        ticketCrudService.create(null,
                planetCrudService.getById("FIR"),
                planetCrudService.getById("SEC"));

        ticketCrudService.create(clientCrudService.getById(3),
                null,
                planetCrudService.getById("SEC"));
    }
}
