package ee.company.crm.domain.service.user.profile.property;

import ee.company.crm.application.web.profile.ProfileDto;

public interface ProfileProperty {
    void updatePropertyFromProfile(ProfileDto profile);

    void connectPropertyWithProfile(ProfileDto profile);
}
