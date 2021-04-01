package ee.company.crm.domain.persistence.user;

import lombok.Data;

@Data
public class UserEntity {
    private Long id;
    private String username;
    private String password;
}
