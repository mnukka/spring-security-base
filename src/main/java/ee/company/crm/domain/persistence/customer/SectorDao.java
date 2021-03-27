package ee.company.crm.domain.persistence.customer;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SectorDao {
    List<SectorEntity> findAll();

    SectorEntity findById(Long id);
}
