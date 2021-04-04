package ee.company.crm.domain.service.user.profile;

import ee.company.crm.application.web.profile.ProfileDto;
import ee.company.crm.domain.persistence.user.profile.ProfileDao;
import ee.company.crm.domain.persistence.user.profile.ProfileEntity;
import ee.company.crm.domain.service.user.UserService;
import ee.company.crm.domain.service.user.profile.property.ProfileProperty;
import ee.company.crm.domain.service.user.profile.property.PropertyInstance;
import ee.company.crm.domain.service.user.profile.property.PropertyManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileDao profileDao;
    private final UserService userService;
    private final PropertyManager propertyManager;

    public ProfileService(ProfileDao profileDao, UserService userService, PropertyManager propertyManager) {
        this.profileDao = profileDao;
        this.userService = userService;
        this.propertyManager = propertyManager;
    }

    public ProfileDto fetchPartialOrEmpty() {
        return fetch().orElse(new ProfileDto());
    }

    public ProfileDto fetchFullOrEmpty() {
        ProfileDto profile = fetchPartialOrEmpty();
        List<ProfileProperty> propertyList = propertyManager.fetchAllProfileProperties();
        propertyList.forEach(p -> p.enrichProfileWithProperty(profile));
        return profile;
    }

    @Transactional
    public void updateWithProperties(ProfileDto profileDto, List<PropertyInstance> propertyInstances) {
        upsert(profileDto);

        if (propertyInstances.isEmpty()) {
            return;
        }

        List<ProfileProperty> profileProperties = propertyManager.getProperties(propertyInstances);
        profileProperties.forEach(p -> p.updatePropertyFromProfile(profileDto));
    }

    private void upsert(ProfileDto profileDto) {
        final long userId = userService.getCurrentUserId();
        var currentEntity = profileDao.findByUserid(userId);

        final ModelMapper modelMapper = new ModelMapper();
        ProfileEntity newEntity = modelMapper.map(profileDto, ProfileEntity.class);
        newEntity.setUserId(userId);

        currentEntity.ifPresentOrElse(
                ent -> profileDao.update(newEntity),
                () -> {
                    final long profileId = profileDao.insert(newEntity);
                    profileDto.setId(profileId);
                });
    }

    private Optional<ProfileDto> fetch() {
        final long userId = userService.getCurrentUserId();
        final ModelMapper modelMapper = new ModelMapper();
        final var profileEntity = profileDao.findByUserid(userId);
        return profileEntity.map(ent -> modelMapper.map(ent, ProfileDto.class));
    }
}
