package ee.company.crm.domain.persistence.user.profile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import util.PostgresContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserSectorDaoITest {

    PostgresContainer postgresContainer = PostgresContainer.getInstance();

    @Autowired
    UserSectorDao userSectorDao;

    @Test
    @Transactional
    void insert_insert1UserSector_findOne() {
        // given
        long userId = 3;
        long sectorId = 2;
        var sectorEntity = UserSectorEntity.builder().id(1).sectorId(sectorId).userId(userId).build();

        // when
        userSectorDao.insert(sectorEntity);
        var result = userSectorDao.findByUserId(3);

        // then
        assertEquals(1, result.size());
        assertEquals(userId, result.get(0).getUserId());
        assertEquals(sectorId, result.get(0).getSectorId());
    }
}