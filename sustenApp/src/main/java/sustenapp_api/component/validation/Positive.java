package sustenapp_api.component.validation;

public class Positive {
    public static boolean isValid(Double value){
        return value > 0;
    }

    public static boolean isValid(Integer value){
        return value > 0;
    }
}
