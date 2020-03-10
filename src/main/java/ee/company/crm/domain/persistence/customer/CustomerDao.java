package ee.company.crm.domain.persistence.customer;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CustomerDao {
    public long insert(CustomerEntity customerEntity);

    public CustomerEntity findById(Integer customerId);

    public List<CustomerEntity> findByUserid(Long userId);

    void update(CustomerEntity entity);

    CustomerEntity findByUserAndCustomerId(Long userId, long customerId);
}
