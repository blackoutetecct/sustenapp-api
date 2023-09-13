package sustenapp_api.component.treatment;

public class StringTreatment {
    public static String removeEspeciais(String texto) {
        return texto.replaceAll("/[^a-z0-9]/gi", "");
    }
}
