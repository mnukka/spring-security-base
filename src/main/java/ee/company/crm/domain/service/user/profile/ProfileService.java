package ee.company.crm.domain.service.user.profile;

import ee.company.crm.application.spring.security.user.UserSession;
import ee.company.crm.application.web.profile.ProfileDto;
import ee.company.crm.domain.persistence.user.profile.ProfileDao;
import ee.company.crm.domain.persistence.user.profile.ProfileEntity;
import ee.company.crm.domain.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileDao profileDao;
    private final UserService userService;
    private final UserSectorService userSectorService;

    public ProfileService(ProfileDao profileDao, UserService userService, UserSectorService userSectorService) {
        this.profileDao = profileDao;
        this.userService = userService;
        this.userSectorService = userSectorService;
    }

    public Optional<ProfileDto> fetch() {
        final UserSession currentUser = userService.getCurrentUserFromSession();
        final ModelMapper modelMapper = new ModelMapper();
        final var profileEntity = profileDao.findByUserid(currentUser.getId());
        return profileEntity.map(ent -> modelMapper.map(ent, ProfileDto.class));
    }

    public ProfileDto fetchOrEmpty() {
        return fetch().orElse(new ProfileDto());
    }

    public ProfileDto fetchWithSectorsOrEmpty() {
        final long userId = userService.getCurrentUserFromSession().getId();
        final var userSectors = userSectorService.findSectorsByUserId(userId);

        if (userSectors.isEmpty()) {
            return fetchOrEmpty();
        }

        ProfileDto profile = fetchOrEmpty();
        profile.setSectorIds(userSectors);
        return profile;
    }

    @Transactional
    public void upsert(ProfileDto profileDto) {
        final var userProfileEntity = fetch();
        if (userProfileEntity.isPresent()) {
            profileDto.setId(userProfileEntity.get().getId());
            userSectorService.updateSectorsForUser(userProfileEntity.get().getId(), profileDto.getSectorIds());
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
        UserSession currentUser = userService.getCurrentUserFromSession();
        ProfileEntity profileEntity = mapCustomerData(profileDto);
        profileEntity.setUserId(currentUser.getId());
        profileDao.insert(profileEntity);
    }

    private ProfileEntity mapCustomerData(ProfileDto profileDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(profileDto, ProfileEntity.class);
    }
}
