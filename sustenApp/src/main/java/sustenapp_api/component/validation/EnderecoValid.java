package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sustenapp_api.dto.POST.EnderecoDto;
import sustenapp_api.dto.PUT.EnderecoPutDto;

import java.util.stream.Stream;

import static sustenapp_api.component.treatment.StringTreatment.removeEspeciais;

@Component
@RequiredArgsConstructor
public class EnderecoValid {
    private final RestTemplate cliente;

    public boolean isValid(EnderecoDto value) {
        return verifyEndereco(value);
    }

    public boolean isValid(EnderecoPutDto value) {
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

    private boolean verifyEndereco(EnderecoPutDto endereco) {
        Endereco consultaCep = consultaCEP(endereco.getCep());

        return Stream.of(
                removeEspeciais(consultaCep.logradouro).equalsIgnoreCase(removeEspeciais(endereco.getLogradouro())),
                removeEspeciais(consultaCep.localidade).equalsIgnoreCase(removeEspeciais(endereco.getCidade())),
                removeEspeciais(consultaCep.uf).equalsIgnoreCase(removeEspeciais(endereco.getEstado()))
        ).allMatch(valor -> valor.equals(true));
    }

    private Endereco consultaCEP(String cep) {
        return cliente.getForEntity(
                "https://viacep.com.br/ws/"+cep+"/json/", Endereco.class
        ).getBody();
    }

    private record Endereco (String logradouro, String localidade, String uf) { }
}
