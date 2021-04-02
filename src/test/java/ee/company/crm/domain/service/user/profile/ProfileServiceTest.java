package ee.company.crm.domain.service.user.profile;

import ee.company.crm.application.web.profile.ProfileDto;
import ee.company.crm.domain.persistence.user.profile.ProfileDao;
import ee.company.crm.domain.persistence.user.profile.ProfileEntity;
import ee.company.crm.domain.service.user.UserService;
import ee.company.crm.domain.service.user.profile.property.SectorProperty;
import org.junit.jupiter.api.Assertions;
import org.modelmapper.ModelMapper;
import util.ITestPostgresqlContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import util.SpringUserSessionTestUtil;

import java.util.List;

@Testcontainers
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProfileServiceTest {

    public static final PostgreSQLContainer<?> postgreSQLContainer;

    static {
        postgreSQLContainer = ITestPostgresqlContainer.getInstance();
        postgreSQLContainer.start();
    }

    @Autowired
    ProfileService profileService;

    @Autowired
    UserService userService;

    @Autowired
    ProfileDao profileDao;


    @Test
    @Transactional
    void update_sectorProperty_connectedToProfileInDb() {
        ProfileDto profileDto = createUserInDbWithSpringSecurity("newGuy");
        final ModelMapper modelMapper = new ModelMapper();
        ProfileEntity profileEntity = modelMapper.map(profileDto, ProfileEntity.class);
        profileDao.insert(profileEntity);

        profileService.updateWithProperties(profileDto, List.of(SectorProperty.class));
        ProfileDto profileFromDb = profileService.fetchFullOrEmpty();
        Assertions.assertEquals(profileDto.getSectorIds(), profileFromDb.getSectorIds());
    }

    private ProfileDto createUserInDbWithSpringSecurity(String username) {
        long userId = userService.insertUser(username, "whatever");
        SpringUserSessionTestUtil.createSecurityContextUser(userId, username);
        return createProfileDto(userId);
    }

    private ProfileDto createProfileDto(Long userId) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setUserId(userId);
        profileDto.setFullName("Whatever");
        profileDto.setSectorIds(List.of(1L, 2L));
        profileDto.setTermsOfAgreement(false);
        return profileDto;
    }
}