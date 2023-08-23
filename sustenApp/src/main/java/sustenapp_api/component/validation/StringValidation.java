package sustenapp_api.component.validation;

public class StringValidation {
    public boolean isValid(String value) {
        return value.matches("^[a-zA-Z]+$");
    }
}
