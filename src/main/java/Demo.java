import entity.Client;
import entity.Planet;
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
        Client client = new Client();
        client.setName("Emma");

        ClientCrudService clientCrudService = new ClientCrudService();
        clientCrudService.create(client);
        clientCrudService.deleteById(7L);

        System.out.println(clientCrudService.getAll());
    }

    public static void planetCrudServiceDemo() {
        Planet planet = new Planet();
        PlanetCrudService planetCrudService = new PlanetCrudService();

        planet.setId("SIXTH");
        planet.setName("PlanetSixth");

        planetCrudService.create(planet);
        planetCrudService.deleteById("THIRD");

        System.out.println(planetCrudService.getAll());
    }
}
