package sustenapp_api.component.constraint.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import sustenapp_api.component.constraint.classVerify.EnderecoVerifyClass;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnderecoVerifyClass.class)
public @interface EnderecoVerify {
    String message() default "";
    Class<?>[ ] groups() default { };
    Class<? extends Payload>[ ] payload() default { };
}