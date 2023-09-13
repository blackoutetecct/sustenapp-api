package sustenapp_api.component.validation;

public class StringValid {
    public static boolean isValid(String value) {
        return value.matches("^[a-zA-Z]+$");
    }
}
