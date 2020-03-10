package ee.company.crm.domain.persistence.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    public UserEntity findByUsername(String username);
}
