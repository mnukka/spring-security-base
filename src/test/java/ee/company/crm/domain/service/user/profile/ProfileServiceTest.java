package ee.company.crm.domain.service.user.profile;

import ee.company.crm.application.web.profile.ProfileDto;
import ee.company.crm.domain.persistence.user.UserDao;
import ee.company.crm.domain.persistence.user.UserEntity;
import ee.company.crm.domain.persistence.user.profile.ProfileDao;
import ee.company.crm.domain.persistence.user.profile.ProfileEntity;
import ee.company.crm.domain.service.user.UserService;
import ee.company.crm.domain.service.user.profile.property.SectorProperty;
import org.junit.Assert;
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
import java.util.Optional;

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
    void update_agreementProperty_connectedToProfileInDb() {
        ProfileDto profileDto = createUserInDbWithSpringSecurity("newGuy");
        final ModelMapper modelMapper = new ModelMapper();
        ProfileEntity profileEntity = modelMapper.map(profileDto, ProfileEntity.class);
        profileDao.insert(profileEntity);

        profileService.update(profileDto, List.of(SectorProperty.class));
        ProfileDto profileFromDb = profileService.fetchFullProfile();
        Assertions.assertEquals(profileDto.getSectorIds(), profileFromDb.getSectorIds());
    }


    @Test
    @Transactional
    void upsert_noProfile_createsNewProfile() {
        ProfileDto profileDto = createUserInDbWithSpringSecurity("SandyShores");
        profileService.upsert(profileDto);

        Optional<ProfileDto> profileFromDb = profileService.fetch();
        Assertions.assertTrue(profileFromDb.isPresent());

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