package sustenapp_api.component.validation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sustenapp_api.dto.POST.EnderecoDto;

import java.util.stream.Stream;

import static sustenapp_api.component.treatment.StringTreatment.removeEspeciais;

@Component
@RequiredArgsConstructor
public class EnderecoValidation {
    private final RestTemplate cliente;

    public boolean isValid(EnderecoDto value) {
        return verifyEndereco(value);
    }

    private boolean verifyEndereco(EnderecoDto endereco) {
        Endereco consultaCep = consultaCEP(endereco.getCep());

        return Stream.of(
                removeEspeciais(consultaCep.logradouro).equalsIgnoreCase(removeEspeciais(endereco.getLogradouro())),
                removeEspeciais(consultaCep.localidade).equalsIgnoreCase(removeEspeciais(endereco.getCidade())),
                removeEspeciais(consultaCep.uf).equalsIgnoreCase(removeEspeciais(endereco.getEstado()))
        ).allMatch(valor -> valor.equals(true));
    }

    private Endereco consultaCEP(java.lang.@NotNull @NotEmpty String cep) {
        return cliente.getForEntity(
                "https://viacep.com.br/ws/"+cep+"/json/", Endereco.class
        ).getBody();
    }

    private record Endereco (String logradouro, String localidade, String uf) { }
}
