package ee.company.crm.domain.service.user.profile.property;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PropertyManager {

    private final List<ProfileProperty> propertyList;

    public PropertyManager(List<ProfileProperty> propertyList) {
        this.propertyList = propertyList;
    }

    public List<ProfileProperty> fetchAllProfileProperties() {
        return propertyList;
    }

    public List<ProfileProperty> getProperties(List<PropertyInstance> propertyInstances) {
        return propertyInstances.stream().map(PropertyInstance::get).collect(Collectors.toList());
    }
}
