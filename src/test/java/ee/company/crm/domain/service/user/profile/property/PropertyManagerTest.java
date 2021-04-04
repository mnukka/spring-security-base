package ee.company.crm.domain.service.user.profile.property;

import ee.company.crm.domain.service.user.profile.property.sector.SectorInstance;
import ee.company.crm.domain.service.user.profile.property.sector.SectorProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest
class PropertyManagerTest {

    @Autowired
    PropertyManager propertyManager;

    @Autowired
    SectorProperty sectorProperty;

    @Mock
    SectorInstance sectorInstance;

    @Test
    void fetchAllProfileProperties_withProfileProperty_findsSectorProperty() {
        // when
        List<ProfileProperty> propertyList = propertyManager.fetchAllProfileProperties();
        // then
        propertyList.forEach(p -> Assertions.assertEquals(SectorProperty.class, p.getClass()));
    }

    @Test
    void getProperties_withSectorInstance_findsSectorProperty() {
        // given
        when(sectorInstance.get()).thenReturn(sectorProperty);

        // when
        List<ProfileProperty> propertyList = propertyManager.getProperties(List.of(sectorInstance));

        // then
        propertyList.forEach(p -> Assertions.assertEquals(SectorProperty.class, p.getClass()));
    }
}