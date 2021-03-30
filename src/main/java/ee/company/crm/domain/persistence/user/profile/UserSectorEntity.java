package ee.company.crm.domain.persistence.user.profile;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserSectorEntity {
    private long id;
    private long userId;
    private long sectorId;
}
