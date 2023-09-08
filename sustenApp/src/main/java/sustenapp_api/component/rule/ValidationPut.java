package sustenapp_api.component.rule;

public interface ValidationPut<E> {
    boolean validatePut(E value);
    void validatedPut(E value);
}
