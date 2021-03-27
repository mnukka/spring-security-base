package ee.company.crm.application.spring.constraint;

import ee.company.crm.domain.service.customer.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SectorExistsValidator implements ConstraintValidator<SectorExists, Long> {
    @Autowired
    CustomerDetailsService customerDetailsService;

    public void initialize(SectorExists constraint) {
    }

    public boolean isValid(Long countryId, ConstraintValidatorContext context) {
       if (countryId == null || countryId < 1) {
          return false;
       }

       try {
          customerDetailsService.getSectorById(countryId);
       } catch (Exception ex) {
          return false;
       }

       return true;
    }
}
