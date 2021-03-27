package ee.company.crm.domain.service.customer;

import ee.company.crm.application.spring.constraint.SectorExists;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerDto {

    private Long id;

    private int parentId;

    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Surname is required.")
    private String lastName;

    @NotBlank(message = "Username is required.")
    private String username;

    private String email;

    @NotBlank(message = "Address is required.")
    private String address;

    @SectorExists
    private Long sectorId;

    private int level;
}
