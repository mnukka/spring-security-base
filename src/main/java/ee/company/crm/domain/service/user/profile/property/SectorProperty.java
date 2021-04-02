package ee.company.crm.domain.service.user.profile.property;

import ee.company.crm.application.web.profile.ProfileDto;
import ee.company.crm.domain.service.user.profile.UserSectorService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SectorProperty implements ProfileProperty {


    private UserSectorService userSectorService;

    public SectorProperty(UserSectorService userSectorService) {
        this.userSectorService = userSectorService;
    }

    @Override
    public void updatePropertyFromProfile(ProfileDto profile) {
        userSectorService.updateSectorsForUser(profile.getUserId(), profile.getSectorIds());
    }

    @Override
    public void connectPropertyWithProfile(ProfileDto profile) {
        final List<Long> userSectors = userSectorService.findSectorsByUserId(profile.getUserId());
        profile.setSectorIds(userSectors);
    }
}
