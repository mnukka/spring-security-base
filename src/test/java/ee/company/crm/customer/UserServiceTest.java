package ee.company.crm.customer;

import ee.company.crm.application.web.profile.ProfileDto;
import ee.company.crm.domain.persistence.user.profile.ProfileDao;
import ee.company.crm.domain.persistence.user.profile.ProfileEntity;
import ee.company.crm.domain.service.user.UserService;
import ee.company.crm.domain.service.user.profile.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Captor
    ArgumentCaptor<ProfileEntity> customerEntityCaptor;

    @Mock
    ProfileDao profileDao;

    @Mock
    UserService userService;

    @InjectMocks
    ProfileService profileService;

    @Test
    void updateWithProperties_profileNotFound_insertProfile() {
        // given
        long userId = 1;
        ProfileDto profileDto = createCustomerDto(userId);
        when(userService.getCurrentUserId()).thenReturn(userId);

        // when
        profileService.updateWithProperties(profileDto, List.of());

        // then
        verify(profileDao).insert(customerEntityCaptor.capture());
        assertEquals(userId, customerEntityCaptor.getValue().getUserId());
        assertEquals(profileDto.getFullName(), customerEntityCaptor.getValue().getFullName());
        assertEquals(profileDto.isTermsOfAgreement(), customerEntityCaptor.getValue().isTermsOfAgreement());
    }

    @Test
    void updateWithProperties_profileFound_updateProfile() {
        // given
        long userId = 1;
        ProfileDto profileDto = createCustomerDto(userId);
        ProfileEntity existingProfile = new ProfileEntity();
        existingProfile.setUserId(userId);
        when(userService.getCurrentUserId()).thenReturn(userId);
        when(profileDao.findByUserid(userId)).thenReturn(Optional.of(existingProfile));

        // when
        profileService.updateWithProperties(profileDto, List.of());

        // then
        verify(profileDao).update(customerEntityCaptor.capture());
        assertEquals(userId, customerEntityCaptor.getValue().getUserId());
        assertEquals(profileDto.getFullName(), customerEntityCaptor.getValue().getFullName());
        assertEquals(profileDto.isTermsOfAgreement(), customerEntityCaptor.getValue().isTermsOfAgreement());
    }

    @Test
    void updateWithProperties_sectorsGiven_() {
        long userId = 1;
        ProfileDto profileDto = createCustomerDto(userId);

        // profileService.updateWithProperties(profileDto, List.of(new SectorInstance()));

        //profileService.updateWithProperties(profileDto, List.of(new SectorInstance()));

        //verify(propertyManager).getProperty()
    }

    private ProfileDto createCustomerDto(long userId) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(userId);
        profileDto.setFullName("fullName");
        profileDto.setTermsOfAgreement(true);
        return profileDto;
    }
}
