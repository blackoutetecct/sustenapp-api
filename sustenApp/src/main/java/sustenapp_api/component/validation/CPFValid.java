package sustenapp_api.component.validation;

import br.com.caelum.stella.validation.CPFValidator;

public class CPFValid {
    public static boolean isValid(String value) {
        return verifyCpf(value);
    }

    private static boolean verifyCpf(String cpf) {
        try{
            new CPFValidator().assertValid(cpf);
            return true;
        } catch (Exception ignored){}

        return false;
    }
}
