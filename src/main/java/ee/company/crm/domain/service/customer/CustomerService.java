package ee.company.crm.domain.service.customer;

import ee.company.crm.application.spring.security.user.CustomUser;
import ee.company.crm.domain.persistence.customer.CustomerDao;
import ee.company.crm.domain.persistence.customer.CustomerEntity;
import ee.company.crm.domain.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerDao customerDao;
    private UserService userService;

    public CustomerService(CustomerDao customerDao, UserService userService) {
        this.customerDao = customerDao;
        this.userService = userService;
    }

    public Optional<CustomerDto> getCustomerByCustomerId(Integer customerId) {
        CustomerEntity customerEntity = customerDao.findById(customerId);
        if (customerEntity == null) {
            return Optional.empty();
        }

        ModelMapper modelMapper = new ModelMapper();
        CustomerDto customerDto = modelMapper.map(customerEntity, CustomerDto.class);
        return Optional.of(customerDto);
    }

    public Long createCustomer(CustomerDto customerDto) {
        CustomUser currentUser = userService.getCurrentUserFromSession();
        CustomerEntity entity = mapCustomerData(customerDto);
        entity.setUserId(currentUser.getId());
        customerDao.insert(entity);
        return entity.getId();
    }

    public CustomerEntity mapCustomerData(CustomerDto customerDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerDto, CustomerEntity.class);
    }

    public List<CustomerDto> findAll() {
        CustomUser currentUser = userService.getCurrentUserFromSession();
        List<CustomerEntity> customers = customerDao.findByUserid(currentUser.getId());
        final ModelMapper modelMapper = new ModelMapper();
        return customers.stream().map(p -> modelMapper.map(p, CustomerDto.class)).collect(Collectors.toList());
    }

    public CustomerDto findByUserAndCustomerId(Long userId, long customerId) {
        CustomerEntity customerEntity = customerDao.findByUserAndCustomerId(userId, customerId);
        if (customerEntity == null) return null;
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerEntity, CustomerDto.class);
    }

    public void updateCustomer(CustomerDto customerDto) {
        final ModelMapper modelMapper = new ModelMapper();
        CustomerEntity entity = modelMapper.map(customerDto, CustomerEntity.class);
        customerDao.update(entity);
    }
}
