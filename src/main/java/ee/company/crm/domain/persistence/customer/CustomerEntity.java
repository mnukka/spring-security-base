package ee.company.crm.domain.persistence.customer;

import lombok.Data;

@Data
public class CustomerEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;
    private Long sectorId;
    private Long userId;
}
