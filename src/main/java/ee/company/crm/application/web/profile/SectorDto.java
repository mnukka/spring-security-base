package ee.company.crm.application.web.profile;

import lombok.Data;

@Data
public class SectorDto {
    private Long id;
    private String sector;
    private int level;
}
