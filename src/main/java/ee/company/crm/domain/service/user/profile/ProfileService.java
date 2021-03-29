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

    public ProfileService(ProfileDao profileDao, UserService userService) {
        this.profileDao = profileDao;
        this.userService = userService;
    }

    public Optional<ProfileDto> find() {
        UserSession currentUser = userService.getCurrentUserFromSession();
        final ModelMapper modelMapper = new ModelMapper();
        Optional<ProfileEntity> entity = profileDao.findByUserid(currentUser.getId());
        return entity.map(profileEntity -> modelMapper.map(profileEntity, ProfileDto.class));
    }

    public void upsert(ProfileDto profileDto) {
        Optional<ProfileDto> entity = find();
        if (entity.isPresent()) {
            profileDto.setId(entity.get().getId());
            update((profileDto));
        } else {
            create(profileDto);
        }
    }

    @Transactional
    public void update(ProfileDto profileDto) {
        final ModelMapper modelMapper = new ModelMapper();
        ProfileEntity entity = modelMapper.map(profileDto, ProfileEntity.class);
        profileDao.update(entity);
    }

    @Transactional
    public void create(ProfileDto profileDto) {
        UserSession currentUser = userService.getCurrentUserFromSession();
        ProfileEntity entity = mapCustomerData(profileDto);
        entity.setUserId(currentUser.getId());
        profileDao.insert(entity);
    }

    private ProfileEntity mapCustomerData(ProfileDto profileDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(profileDto, ProfileEntity.class);
    }


}
