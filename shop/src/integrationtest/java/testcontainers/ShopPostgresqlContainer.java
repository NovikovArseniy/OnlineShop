package testcontainers;

import org.testcontainers.containers.PostgreSQLContainer;

public class ShopPostgresqlContainer extends PostgreSQLContainer<ShopPostgresqlContainer> {
    public static final String IMAGE_VERSION = "postgres:11.1";
    private static ShopPostgresqlContainer container;

    private ShopPostgresqlContainer(){
        super(IMAGE_VERSION);
    }

    public static ShopPostgresqlContainer getInstance(){
        if (container == null){
            container = new ShopPostgresqlContainer();
        }
        return container;
    }

    @Override
    public void start(){
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop(){

    }

}
