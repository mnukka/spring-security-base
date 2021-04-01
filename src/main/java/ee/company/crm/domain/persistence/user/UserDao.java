package ee.company.crm.domain.persistence.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    UserEntity findByUsername(String username);
    long insert(UserEntity userEntity);
}
