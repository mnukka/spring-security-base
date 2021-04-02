package ee.company.crm.domain.service.user.profile;

import ee.company.crm.application.web.profile.ProfileDto;
import ee.company.crm.domain.persistence.user.profile.ProfileDao;
import ee.company.crm.domain.persistence.user.profile.ProfileEntity;
import ee.company.crm.domain.service.user.UserService;
import ee.company.crm.domain.service.user.profile.property.ProfileProperty;
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
    private final UserSectorService userSectorService;
    private PropertyManager propertyManager;

    public ProfileService(ProfileDao profileDao, UserService userService, UserSectorService userSectorService, PropertyManager propertyManager) {
        this.profileDao = profileDao;
        this.userService = userService;
        this.userSectorService = userSectorService;
        this.propertyManager = propertyManager;
    }

    public Optional<ProfileDto> fetch() {
        final long userId = userService.getCurrentUserId();
        final ModelMapper modelMapper = new ModelMapper();
        final var profileEntity = profileDao.findByUserid(userId);
        return profileEntity.map(ent -> modelMapper.map(ent, ProfileDto.class));
    }

    public ProfileDto fetchOrEmpty() {
        return fetch().orElse(new ProfileDto());
    }

    public ProfileDto fetchFullProfile() {
        ProfileDto profile = fetchOrEmpty();
        List<ProfileProperty> propertyList = propertyManager.fetchAllProfileProperties();
        propertyList.forEach(p -> p.connectPropertyWithProfile(profile));
        return profile;
    }

    @Transactional
    public <T extends ProfileProperty> void update(ProfileDto profileDto, List<Class<T>> propertyInterfaces) {
        List<ProfileProperty> profileProperties = propertyManager.getProperties(propertyInterfaces);
        profileProperties.forEach(p -> p.updatePropertyFromProfile(profileDto));
    }

    @Transactional
    public void upsert(ProfileDto profileDto) {
        final var profileDbDto = fetch();
        if (profileDbDto.isPresent()) {
            profileDto.setId(profileDbDto.get().getId());
            userSectorService.updateSectorsForUser(profileDbDto.get().getUserId(), profileDto.getSectorIds());
            update((profileDto));
        } else {
            create(profileDto);
        }
    }

    @Transactional
    public void update(ProfileDto profileDto) {
        final ModelMapper modelMapper = new ModelMapper();
        ProfileEntity profileEntity = modelMapper.map(profileDto, ProfileEntity.class);
        profileDao.update(profileEntity);
    }

    @Transactional
    public void create(ProfileDto profileDto) {
        final long userId = userService.getCurrentUserId();
        final ProfileEntity profileEntity = mapCustomerData(profileDto);
        profileEntity.setUserId(userId);
        userSectorService.updateSectorsForUser(profileDto.getUserId(), profileDto.getSectorIds());
        profileDao.insert(profileEntity);
    }

    private ProfileEntity mapCustomerData(ProfileDto profileDto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(profileDto, ProfileEntity.class);
    }
}
