package sustenapp_api.component.constraint.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import sustenapp_api.component.constraint.classVerify.CPFVerifyClass;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CPFVerifyClass.class)
public @interface CPFVerify {
    String message() default "";
    Class<?>[ ] groups() default { };
    Class<? extends Payload>[ ] payload() default { };
}