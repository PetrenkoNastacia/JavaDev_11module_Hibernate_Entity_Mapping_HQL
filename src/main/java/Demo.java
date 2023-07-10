import hibernateUtils.HibernateUtil;
import service.ClientCrudService;
import service.PlanetCrudService;

import org.flywaydb.core.Flyway;
import java.util.ResourceBundle;

public class Demo {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("hibernate");
    private static final String JDBC_URL = "hibernate.connection.url";

    public static void main(String[] args) {
        Flyway.configure()
                .dataSource(resourceBundle.getString(JDBC_URL), null, null)
                .load()
                .migrate();
        clientCrudServiceDemo();
        planetCrudServiceDemo();

        HibernateUtil.getInstance().close();
    }

    public static void clientCrudServiceDemo() {
        ClientCrudService clientCrudService = new ClientCrudService();
        clientCrudService.create("NewClient");
        System.out.println(clientCrudService.getById(11));
        clientCrudService.update(6, "UpdatedClient");
        clientCrudService.deleteById(7L);
        System.out.println(clientCrudService.getAll());

    }

    public static void planetCrudServiceDemo() {
        PlanetCrudService planetCrudService = new PlanetCrudService();
        planetCrudService.create("NEWPLNT", "NewPlanet");
        System.out.println(planetCrudService.getById("PL4"));
        planetCrudService.update("FIRST", "UpdatedPlanet");
        planetCrudService.deleteById("FOURTH");
        System.out.println(planetCrudService.getAll());

    }
}
