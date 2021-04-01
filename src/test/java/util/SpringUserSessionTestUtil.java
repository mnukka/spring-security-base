package util;

import ee.company.crm.application.spring.security.user.UserSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Collections;

public class SpringUserSessionTestUtil {
    public static UserSession createUser(String username) {
        Collection<? extends GrantedAuthority> role = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")
        );

        return new UserSession(
                username,
                "b",
                true,
                true,
                true,
                false,
                role,
                1
        );
    }

    public static void createSecurityContextUser(Long userId, String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UserSession session = createUser(username);
        session.setId(userId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                session,
                session.getPassword(),
                session.getAuthorities()
        );
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }
}
