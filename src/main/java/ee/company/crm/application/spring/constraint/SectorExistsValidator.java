package ee.company.crm.application.spring.constraint;

import ee.company.crm.application.web.profile.SectorDto;
import ee.company.crm.domain.service.sector.SectorService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;

public class SectorExistsValidator implements ConstraintValidator<SectorExists, List<Long>> {

    @Autowired
    private SectorService sectorService;
    private List<Long> sectorCache;

    public void initialize(SectorExists constraint) {
        sectorCache = sectorService.findAllSectors().stream().map(SectorDto::getId).collect(Collectors.toList());
    }

    public boolean isValid(List<Long> sectorIds, ConstraintValidatorContext context) {
       if (sectorIds == null || sectorIds.isEmpty()) {
          return false;
       }

       try {
           return sectorCache.containsAll(sectorIds);
       } catch (Exception ex) {
          return false;
       }
    }
}
