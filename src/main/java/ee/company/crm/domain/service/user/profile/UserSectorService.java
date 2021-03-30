package ee.company.crm.domain.service.user.profile;

import ee.company.crm.domain.persistence.user.profile.UserSectorDao;
import ee.company.crm.domain.persistence.user.profile.UserSectorEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSectorService {

    private final UserSectorDao userSectorDao;

    public UserSectorService(UserSectorDao userSectorDao) {
        this.userSectorDao = userSectorDao;
    }

    @Transactional
    public void updateSectorsForUser(long userId, List<Long> sectorIds) {
        if (sectorIds.isEmpty()) {
            return;
        }

        userSectorDao.disableByUserId(userId);
        sectorIds.forEach(p -> userSectorDao.insert(UserSectorEntity.builder().sectorId(p).userId(userId).build()));
    }

    public List<Long> findSectorsByUserId(long userId) {
        var userSectors = userSectorDao.findByUserId(userId);
        if (userSectors.isEmpty()) {
            return Collections.emptyList();
        }

        return userSectorDao.findByUserId(userId).stream().map(UserSectorEntity::getSectorId).collect(Collectors.toList());
    }
}
