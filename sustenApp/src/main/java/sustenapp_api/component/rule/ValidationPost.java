package sustenapp_api.component.rule;

public interface ValidationPost<E> {
    boolean validatePost(E value);
    void validatedPost(E value);
}
