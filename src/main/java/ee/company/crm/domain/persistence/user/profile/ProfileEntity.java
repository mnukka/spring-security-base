package ee.company.crm.domain.persistence.user.profile;

import lombok.Data;

@Data
public class ProfileEntity {

    private Long id;
    private String fullName;
    private boolean termsOfAgreement;
    private Long userId;
}
