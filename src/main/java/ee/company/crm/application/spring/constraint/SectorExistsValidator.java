package ee.company.crm.application.spring.constraint;

import ee.company.crm.domain.service.profile.SectorService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SectorExistsValidator implements ConstraintValidator<SectorExists, Long> {
    @Autowired
    SectorService sectorService;

    public void initialize(SectorExists constraint) {
    }

    public boolean isValid(Long countryId, ConstraintValidatorContext context) {
       if (countryId == null || countryId < 1) {
          return false;
       }

       try {
          sectorService.getSectorById(countryId);
       } catch (Exception ex) {
          return false;
       }

       return true;
    }
}
