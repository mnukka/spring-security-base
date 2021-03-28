package ee.company.crm.domain.persistence.profile;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProfileDao {
    public long insert(ProfileEntity profileEntity);

    public ProfileEntity findById(Integer profileId);

    public List<ProfileEntity> findByUserid(Long userId);

    void update(ProfileEntity entity);

    ProfileEntity findByUserAndProfileId(Long userId, long profileId);
}
