package ee.company.crm.domain.service.profile;

import lombok.Data;

@Data
public class SectorDto {
    private Long id;
    private String sector;
    private int level;
}
