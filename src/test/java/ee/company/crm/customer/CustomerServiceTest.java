package ee.company.crm.customer;

import ee.company.crm.application.spring.security.user.UserSession;
import ee.company.crm.domain.persistence.user.profile.ProfileDao;
import ee.company.crm.domain.persistence.user.profile.ProfileEntity;
import ee.company.crm.application.web.profile.ProfileDto;
import ee.company.crm.domain.service.user.profile.ProfileService;
import ee.company.crm.domain.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceTest {

    @Captor
    ArgumentCaptor<ProfileEntity> customerEntityCaptor;

    @Mock
    ProfileDao profileDao;

    @Mock
    UserService userService;

    @Spy
    @InjectMocks
    ProfileService profileService;

    @Test
    void whenCreatingNewCustomer_VerifyEntityMapping() {
        // given
        UserSession user = createUser();
        ProfileDto profileDto = createCustomerDto();

        // when
        when(userService.getCurrentUserFromSession()).thenReturn(user);
        profileService.create(profileDto);

        // then
        verify(profileDao).insert(customerEntityCaptor.capture());
        assertEquals(user.getId(), customerEntityCaptor.getValue().getUserId());
        assertEquals(profileDto.getFullName(), customerEntityCaptor.getValue().getFullName());
        assertEquals(profileDto.isTermsOfAgreement(), customerEntityCaptor.getValue().getTermsOfAgreement());
    }

    private ProfileEntity createCustomerEntity(Integer customerId) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId(Long.valueOf(customerId));
        profileEntity.setFullName("fullName");
        profileEntity.setTermsOfAgreement(true);
        return profileEntity;
    }

    private ProfileDto createCustomerDto() {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setFullName("fullName");
        profileDto.setTermsOfAgreement(true);
        return profileDto;
    }

    private UserSession createUser() {
        Collection<? extends GrantedAuthority> role = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")
        );

        UserSession user = new UserSession(
                "a",
                "b",
                true,
                true,
                true,
                false,
                role,
                1
        );
        return user;
    }
}
