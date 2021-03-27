package ee.company.crm.customer;

import ee.company.crm.application.spring.security.user.CustomUser;
import ee.company.crm.domain.persistence.customer.CustomerDao;
import ee.company.crm.domain.persistence.customer.CustomerEntity;
import ee.company.crm.domain.service.customer.CustomerDto;
import ee.company.crm.domain.service.customer.CustomerService;
import ee.company.crm.domain.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceTest {

    @Captor
    ArgumentCaptor<CustomerEntity> customerEntityCaptor;

    @Mock
    CustomerDao customerDao;

    @Mock
    UserService userService;

    @Spy
    @InjectMocks
    CustomerService customerService;

    @Test
    void whenRetrievingCustomer_IsCustomerDaoExecuted() {
        // given
        Integer customerId = 1;
        CustomerEntity customerEntity = createCustomerEntity(customerId);

        // when
        when(customerDao.findById(customerId)).thenReturn(customerEntity);
        customerService.getCustomerByCustomerId(customerId);

        // then
        verify(customerDao, times(1)).findById(customerId);
    }

    @Test
    void whenCreatingNewCustomer_VerifyEntityMapping() {
        // given
        CustomUser user = createUser();
        CustomerDto customerDto = createCustomerDto();

        // when
        when(userService.getCurrentUserFromSession()).thenReturn(user);
        customerService.createCustomer(customerDto);

        // then
        verify(customerDao).insert(customerEntityCaptor.capture());
        assertEquals(user.getId(), customerEntityCaptor.getValue().getUserId());
        assertEquals(customerDto.getFirstName(), customerEntityCaptor.getValue().getFirstName());
        assertEquals(customerDto.getLastName(), customerEntityCaptor.getValue().getLastName());
        assertEquals(customerDto.getEmail(), customerEntityCaptor.getValue().getEmail());
        assertEquals(customerDto.getUsername(), customerEntityCaptor.getValue().getUsername());
        assertEquals(customerDto.getSectorId(), customerEntityCaptor.getValue().getSectorId());
    }

    private CustomerEntity createCustomerEntity(Integer customerId) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(Long.valueOf(customerId));
        customerEntity.setFirstName("firstName");
        customerEntity.setLastName("lastName");
        customerEntity.setEmail("email");
        customerEntity.setAddress("Addrssss");
        customerEntity.setUsername("user");
        customerEntity.setSectorId(1L);
        return customerEntity;
    }

    private CustomerDto createCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail("email");
        customerDto.setFirstName("firstName");
        customerDto.setLastName("lastName");
        return customerDto;
    }

    private CustomUser createUser() {
        Collection<? extends GrantedAuthority> role = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        CustomUser user = new CustomUser(
                "a",
                "b",
                true,
                true,
                true,
                false,
                role,
                1
        );
        return user;
    }
}
