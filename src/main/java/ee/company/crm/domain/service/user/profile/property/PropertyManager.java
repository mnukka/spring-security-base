package ee.company.crm.domain.service.user.profile.property;

import ee.company.crm.application.spring.context.SpringContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PropertyManager {

    private List<ProfileProperty> propertyList;

    public PropertyManager(List<ProfileProperty> propertyList) {
        this.propertyList = propertyList;
    }

    public List<ProfileProperty> fetchAllProfileProperties() {
        return propertyList;
    }

    public <T extends ProfileProperty> List<ProfileProperty> getProperties(List<Class<T>> propertyInterfaces) {
        return propertyInterfaces.stream().map(SpringContext::getBean).collect(Collectors.toList());
    }
}
