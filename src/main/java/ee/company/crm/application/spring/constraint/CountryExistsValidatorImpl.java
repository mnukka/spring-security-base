package ee.company.crm.application.spring.constraint;

import ee.company.crm.domain.service.customer.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountryExistsValidatorImpl implements ConstraintValidator<CountryExists, Long> {
    @Autowired
    CustomerDetailsService customerDetailsService;

    public void initialize(CountryExists constraint) {
    }

    public boolean isValid(Long countryId, ConstraintValidatorContext context) {
       if (countryId == null || countryId < 1) {
          return false;
       }

       try {
          customerDetailsService.getCountryById(countryId);
       } catch (Exception ex) {
          return false;
       }

       return true;
    }
}
