package ee.company.crm.domain.service.profile;

import ee.company.crm.application.spring.security.user.UserSession;
import ee.company.crm.domain.persistence.profile.ProfileDao;
import ee.company.crm.domain.persistence.profile.ProfileEntity;
import ee.company.crm.domain.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    private ProfileDao profileDao;
    private UserService userService;

    public ProfileService(ProfileDao profileDao, UserService userService) {
        this.profileDao = profileDao;
        this.userService = userService;
    }

    public Optional<ProfileDto> findById(Integer profileId) {
        ProfileEntity profileEntity = profileDao.findById(profileId);
        if (profileEntity == null) {
            return Optional.empty();
        }

        ModelMapper modelMapper = new ModelMapper();
        ProfileDto profileDto = modelMapper.map(profileEntity, ProfileDto.class);
        return Optional.of(profileDto);
    }

    public Long create(ProfileDto profileDto) {
        UserSession currentUser = userService.getCurrentUserFromSession();
        ProfileEntity entity = mapCustomerData(profileDto);
        entity.setUserId(currentUser.getId());
        profileDao.insert(entity);
        return entity.getId();
    }

    public ProfileEntity mapCustomerData(ProfileDto profileDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(profileDto, ProfileEntity.class);
    }

    public Optional<ProfileDto> findByUserAndCustomerId(Long userId, long customerId) {
        final ProfileEntity profileEntity = profileDao.findByUserAndProfileId(userId, customerId);
        if (profileEntity == null) {
            return Optional.empty();
        }
        final ModelMapper modelMapper = new ModelMapper();
        return Optional.of(modelMapper.map(profileEntity, ProfileDto.class));
    }

    public void update(ProfileDto profileDto) {
        final ModelMapper modelMapper = new ModelMapper();
        ProfileEntity entity = modelMapper.map(profileDto, ProfileEntity.class);
        profileDao.update(entity);
    }

    public ProfileDto find() {
        return new ProfileDto();
    }
}
