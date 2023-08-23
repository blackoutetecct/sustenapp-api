package sustenapp_api.component.validation;

public class PositiveOrZero {
    public static boolean isValid(Double value){
        return value >= 0;
    }
}
