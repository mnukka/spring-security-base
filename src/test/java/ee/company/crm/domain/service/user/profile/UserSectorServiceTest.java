package ee.company.crm.domain.service.user.profile;

import ee.company.crm.domain.persistence.user.profile.UserSectorDao;
import ee.company.crm.domain.persistence.user.profile.UserSectorEntity;
import ee.company.crm.util.ITestPostgresqlContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserSectorServiceTest {

    public static final PostgreSQLContainer<?> postgreSQLContainer;

    static {
        postgreSQLContainer = ITestPostgresqlContainer.getInstance();
        postgreSQLContainer.start();
    }

    @Autowired
    UserSectorDao userSectorDao;

    @Test
    @Transactional
    void updateSectorsForUser() {
        var sectorEntity = UserSectorEntity.builder().id(1).sectorId(2).userId(3).build();
        userSectorDao.insert(sectorEntity);
        var result = userSectorDao.findByUserId(3);
        assertEquals(result.get(0).getUserId(), 3);
    }

    @Test
    void findSectorsByUserId() {
    }
}