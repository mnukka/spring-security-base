package ee.company.crm.domain.service.user.profile.property.sector;

import ee.company.crm.application.spring.context.SpringContext;
import ee.company.crm.domain.service.user.profile.property.ProfileProperty;
import ee.company.crm.domain.service.user.profile.property.PropertyInstance;

public class SectorInstance implements PropertyInstance {
    @Override
    public ProfileProperty get() {
        return SpringContext.getBean(SectorProperty.class);
    }
}
