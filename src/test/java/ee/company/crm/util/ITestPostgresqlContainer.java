package ee.company.crm.util;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.testcontainers.containers.*;

public class ITestPostgresqlContainer extends PostgreSQLContainer<ITestPostgresqlContainer> {

    private static final String IMAGE_VERSION = "postgres:13.2-alpine";

    private static ITestPostgresqlContainer container;


    private ITestPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static ITestPostgresqlContainer getInstance() {
        if (container == null) {
            container = new ITestPostgresqlContainer()
                    .withUsername("postgres")
                    .withPassword("postgres")
                    .withCreateContainerCmdModifier(cmd -> {
                        cmd
                                .withHostName("localhost")
                                .withPortBindings(new PortBinding(Ports.Binding.bindPort(5432), new ExposedPort(5432)));
                    });
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        // Thanks for not following interface segregation principle
    }
}
