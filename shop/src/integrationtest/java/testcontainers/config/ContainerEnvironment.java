package testcontainers.config;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import testcontainers.ShopPostgresqlContainer;

@Testcontainers
public class ContainerEnvironment {

    @Container
    public static PostgreSQLContainer postgreSQLContainer = ShopPostgresqlContainer.getInstance();
}
