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
        clientCrudService.createClient("NewClient");
        System.out.println(clientCrudService.getClientById(11));
        clientCrudService.updateClient(6, "UpdatedClient");
        clientCrudService.deleteClientById(7L);
        System.out.println(clientCrudService.getAllClients());

    }

    public static void planetCrudServiceDemo() {
        PlanetCrudService planetCrudService = new PlanetCrudService();
        planetCrudService.createPlanet("NEWPLNT", "NewPlanet");
        System.out.println(planetCrudService.getPlanetById("PL4"));
        planetCrudService.updatePlanet("FIRST", "UpdatedPlanet");
        planetCrudService.deletePlanetById("FOURTH");
        System.out.println(planetCrudService.getAllPlanets());

    }
}
