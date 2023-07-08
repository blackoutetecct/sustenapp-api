package sustenapp_api.component.constraint.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import sustenapp_api.component.constraint.classVerify.UsuarioVerifyClass;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsuarioVerifyClass.class)
public @interface UsuarioVerify {
    String message() default "<mensagem>";
    Class<?>[ ] groups() default { };
    Class<? extends Payload>[ ] payload() default { };
}