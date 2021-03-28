package ee.company.crm.domain.service.user;

import ee.company.crm.application.spring.security.user.UserSession;
import ee.company.crm.domain.persistence.user.UserDao;
import ee.company.crm.domain.persistence.user.UserEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserEntity findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public UserSession getCurrentUserFromSession() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (UserSession) securityContext.getAuthentication().getPrincipal();
    }

    public String getCurrentRole() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
