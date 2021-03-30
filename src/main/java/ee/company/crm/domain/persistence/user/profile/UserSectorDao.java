package ee.company.crm.domain.persistence.user.profile;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserSectorDao {
    long insert(UserSectorEntity entity);

    List<UserSectorEntity> findByUserId(long userId);

    long disableByUserId(long userId);
}
