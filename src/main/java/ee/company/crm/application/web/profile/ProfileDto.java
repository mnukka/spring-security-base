package ee.company.crm.application.web.profile;

import ee.company.crm.application.spring.constraint.SectorExists;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ProfileDto {

    private Long id;

    private Long userId;

    @NotBlank(message = "Name is required.")
    private String fullName;

    @AssertTrue(message = "Agreement to terms are mandatory.")
    private boolean termsOfAgreement;

    @Size(min = 1, max = 5, message = "You can choose between 1 to 5 sectors")
    @SectorExists
    private List<Long> sectorIds;

    public ProfileDto() {
    }

    public ProfileDto(Long userId) {
        this.userId = userId;
    }
}
