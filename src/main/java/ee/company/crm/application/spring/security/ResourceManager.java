package ee.company.crm.application.spring.security;

import ee.company.crm.application.spring.security.user.CustomUser;
import ee.company.crm.domain.service.customer.CustomerDto;
import ee.company.crm.domain.service.customer.CustomerService;
import ee.company.crm.domain.service.user.UserService;
import org.springframework.stereotype.Component;

@Component
public class ResourceManager {
    private UserService userService;
    private CustomerService customerService;

    public ResourceManager(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    public Boolean hasAccessToCustomer(String customerId) {
        if ("anonymousUser".equals(userService.getCurrentRole())) {
            return false;
        }

        try {
            CustomUser user = userService.getCurrentUserFromSession();
            long id = Long.parseLong(customerId);
            CustomerDto customer = customerService.findByUserAndCustomerId(user.getId(), id);
            return customer != null;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
