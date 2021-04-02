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

    public List<ProfileProperty> getProperties(List<Class<? extends ProfileProperty>> propertyInterfaces) {
        return propertyInterfaces.stream().map(SpringContext::getBean).collect(Collectors.toList());
    }

    public ProfileProperty getProperty(Class<? extends ProfileProperty> propertyInterface) {
        return SpringContext.getBean(propertyInterface);
    }
}
