package ee.company.crm.application.spring.security;

import ee.company.crm.application.spring.security.user.UserSession;
import ee.company.crm.domain.service.profile.ProfileDto;
import ee.company.crm.domain.service.profile.ProfileService;
import ee.company.crm.domain.service.user.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ResourceManager {
    private UserService userService;
    private ProfileService profileService;
    private final static String ANONYMOUS_USER = "anonymousUser";

    public ResourceManager(UserService userService, ProfileService profileService) {
        this.userService = userService;
        this.profileService = profileService;
    }

    public Boolean hasAccessToProfile(String profileId) {
        if (ANONYMOUS_USER.equals(userService.getCurrentRole())) {
            return false;
        }

        final long id;
        try {
            id = Long.parseLong(profileId);
        } catch (NumberFormatException ex) {
            return false;
        }

        UserSession user = userService.getCurrentUserFromSession();
        Optional<ProfileDto> profile = profileService.findByUserAndCustomerId(user.getId(), id);
        return profile.isPresent();
    }
}
