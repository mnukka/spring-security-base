package ee.company.crm.domain.service.user.profile.property;

import ee.company.crm.application.web.profile.ProfileDto;
import ee.company.crm.domain.persistence.user.profile.ProfileDao;
import ee.company.crm.domain.persistence.user.profile.ProfileEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TosAgreementProperty implements ProfileProperty {

    private ProfileDao profileDao;

    public TosAgreementProperty(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public void updatePropertyFromProfile(ProfileDto profile) {
        var currentEntity = profileDao.findByUserid(profile.getUserId());
        final ModelMapper modelMapper = new ModelMapper();

        currentEntity.ifPresent(ent -> {
            var newEntity = modelMapper.map(profile, ProfileEntity.class);
            profileDao.update(newEntity);
        });
    }

    @Override
    public void connectPropertyWithProfile(ProfileDto profile) {
        var currentEntity = profileDao.findByUserid(profile.getUserId());
        final ModelMapper modelMapper = new ModelMapper();
        currentEntity.ifPresent(ent -> profile.setTermsOfAgreement(ent.getTermsOfAgreement()));
    }
}
