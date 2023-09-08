package sustenapp_api.component.validation;

public class EmailValid {
    public static boolean isValid(String value){
        return value.contains("@") && value.contains(".com");
    }
}
