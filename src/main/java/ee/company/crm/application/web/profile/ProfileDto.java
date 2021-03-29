package ee.company.crm.application.web.profile;

import ee.company.crm.application.spring.constraint.SectorExists;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ProfileDto {

    private Long id;

    @NotBlank(message = "Name is required.")
    private String fullName;

    @AssertTrue(message = "Agreement to terms are mandatory.")
    private boolean termsOfAgreement;

    @SectorExists
    private List<Long> sectorIds;

    private int level;
}
