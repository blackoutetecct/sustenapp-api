package sustenapp_api.component.rule;

public interface Validation<E, T> {
    boolean validatePost(E value);
    void validatedPost(E value);
    boolean validatePut(T value);
    void validatedPut(T value);
}
