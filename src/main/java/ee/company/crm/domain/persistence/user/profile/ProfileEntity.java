package ee.company.crm.domain.persistence.user.profile;

import lombok.Data;

@Data
public class ProfileEntity {

    private Long id;
    private String fullName;
    private Boolean termsOfAgreement;
    private Long userId;
}
