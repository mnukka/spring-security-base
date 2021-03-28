package ee.company.crm.domain.service.profile;

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

    @NotBlank(message = "Agreement to terms are mandatory.")
    @AssertTrue
    private Boolean termsAgreement;

    @SectorExists
    private List<Long> sectorIds;

    private int level;
}
