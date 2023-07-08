package sustenapp_api.component.constraint.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import sustenapp_api.component.constraint.classVerify.ComodoVerifyClass;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ComodoVerifyClass.class)
public @interface ComodoVerify {
    String message() default "<mensagem>";
    Class<?>[ ] groups() default { };
    Class<? extends Payload>[ ] payload() default { };
}