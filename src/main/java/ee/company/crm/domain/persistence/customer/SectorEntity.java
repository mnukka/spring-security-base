package ee.company.crm.domain.persistence.customer;

import lombok.Data;

@Data
public class SectorEntity {
    private Long id;
    private Long parentId;
    private String country;
    private Boolean enabled;
    private int level;
}
