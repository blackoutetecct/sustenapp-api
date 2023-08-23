package sustenapp_api.component.validation;

public class Size {
    public static boolean isValid(String value, int min){
        return value.length() >= min;
    }

    public static boolean isValid(String value, int min, int max){
        return value.length() >= min && value.length() <= max;
    }
}
