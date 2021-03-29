package ee.company.crm.domain.persistence.user.profile;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserSectorDao {
    long insert(UserSectorEntity entity);
}
