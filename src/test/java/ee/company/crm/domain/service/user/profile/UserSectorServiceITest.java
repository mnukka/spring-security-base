package ee.company.crm.domain.service.user.profile;

import ee.company.crm.domain.persistence.user.profile.UserSectorDao;
import ee.company.crm.domain.persistence.user.profile.UserSectorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import util.PostgresContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserSectorServiceITest {
    PostgresContainer postgresContainer = PostgresContainer.getInstance();

    @Autowired
    UserSectorDao userSectorDao;

    @Autowired
    UserSectorService userSectorService;

    @Test
    @Transactional
    void findSectorsByUserId_insert3UserSectors_findAll() {
        // given
        long userId = 1;
        for (int i = 1; i <= 3; i++) {
            var sectorEntity = UserSectorEntity.builder().id(i).sectorId(i).userId(userId).build();
            userSectorDao.insert(sectorEntity);
        }

        // when
        List<Long> sectors = userSectorService.findSectorsByUserId(userId);

        // then
        assertEquals(3, sectors.size());
    }

    @Test
    @Transactional
    void updateSectorsForUser_insert3SectorsWith2Existing_disable2AndFind3() {
        // given
        long userId = 1;
        for (int i = 1; i <= 2; i++) {
            var sectorEntity = UserSectorEntity.builder().id(i).sectorId(i).userId(userId).build();
            userSectorDao.insert(sectorEntity);
        }

        List<Long> sectorsWhichWillBeDisabled = userSectorService.findSectorsByUserId(userId);
        assertEquals(2, sectorsWhichWillBeDisabled.size());

        // when
        userSectorService.updateSectorsForUser(userId, List.of(5L, 6L, 7L));

        // then
        List<Long> sectors = userSectorService.findSectorsByUserId(userId);
        assertEquals(3, sectors.size());
        assertEquals(List.of(5L, 6L, 7L), sectors);
    }
}