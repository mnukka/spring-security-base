package util;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainer extends PostgreSQLContainer<PostgresContainer> {

    private static final String IMAGE_VERSION = "postgres:13.2-alpine";
    private final static PostgresContainer container;
    static {
        container = new PostgresContainer()
                .withUsername("postgres")
                .withPassword("postgres")
                .withCreateContainerCmdModifier(cmd -> {
                    cmd.withHostConfig(new HostConfig().withPortBindings(
                            new PortBinding(
                                    Ports.Binding.bindPort(5432),
                                    new ExposedPort(5432)
                            )))
                            .withHostName("localhost");
                });
        container.start();
    }

    private PostgresContainer() {
        super(IMAGE_VERSION);
    }

    public static PostgresContainer getInstance() {
        return container;
    }
}
