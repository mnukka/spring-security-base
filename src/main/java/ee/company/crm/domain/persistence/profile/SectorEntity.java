package ee.company.crm.domain.persistence.profile;

import lombok.Data;

@Data
public class SectorEntity {
    private Long id;
    private Long parentId;
    private String sector;
    private Boolean enabled;
    private int level;
}