package ee.company.crm.domain.persistence.user.profile;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface ProfileDao {
    long insert(ProfileEntity profileEntity);

    Optional<ProfileEntity> findByUserid(Long userId);

    void update(ProfileEntity entity);
}
