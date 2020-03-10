package ee.company.crm.application.spring.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CountryExistsValidatorImpl.class)
@Target({ FIELD })
@Retention(RUNTIME)
@Documented
public @interface CountryExists {
    String message() default "Invalid Country";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
