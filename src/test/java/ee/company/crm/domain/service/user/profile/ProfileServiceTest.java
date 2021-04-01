package ee.company.crm.domain.service.user.profile;

import ee.company.crm.application.web.profile.ProfileDto;
import ee.company.crm.domain.persistence.user.UserDao;
import ee.company.crm.domain.persistence.user.UserEntity;
import ee.company.crm.domain.service.user.UserService;
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


    @Test
    @Transactional
    void upsert_noProfile_createsNewProfile() {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setFullName("Whatever");
        profileDto.setSectorIds(List.of(1L, 2L));
        profileDto.setTermsOfAgreement(false);

        createUserInDbWithSpringSecurity("SandyShores");
        var userEntity = userService.findByUsername("SandyShores");

        profileService.upsert(profileDto);

        Optional<ProfileDto> profileFromDb = profileService.fetch();
        System.out.println(1);
    }

    private void createUserInDbWithSpringSecurity(String username) {
        userService.insertUser(username, "whatever");
        SpringUserSessionTestUtil.createSecurityContextUser(4L, username);
    }
}