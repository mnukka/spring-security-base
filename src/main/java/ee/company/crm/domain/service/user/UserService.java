package ee.company.crm.domain.service.user;

import ee.company.crm.application.spring.security.user.UserSession;
import ee.company.crm.domain.persistence.user.UserDao;
import ee.company.crm.domain.persistence.user.UserEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
public class UserService {
    private final UserDao userDao;

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

    @Transactional
    public long insertUser(String username, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(encodedPassword);
        return userDao.insert(userEntity);
    }
}
