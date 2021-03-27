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
    private final static String ANONYMOUS_USER = "anonymousUser";

    public ResourceManager(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    public Boolean hasAccessToCustomer(String customerId) {
        if (ANONYMOUS_USER.equals(userService.getCurrentRole())) {
            return false;
        }

        final long id;
        try {
            id = Long.parseLong(customerId);
        } catch (NumberFormatException ex) {
            return false;
        }

        CustomUser user = userService.getCurrentUserFromSession();
        CustomerDto customer = customerService.findByUserAndCustomerId(user.getId(), id);
        return customer != null;

    }
}
