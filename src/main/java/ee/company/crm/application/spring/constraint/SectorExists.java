package ee.company.crm.application.spring.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = SectorExistsValidator.class)
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@Documented
public @interface SectorExists {
    String message() default "Did you forget to select a sector from the list?";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
