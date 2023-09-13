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
        return verifyEndereco(
                value.getCep(), value.getCidade(), value.getEstado()
        );
    }

    public boolean isValid(EnderecoPutDto value) {
        return verifyEndereco(
                value.getCep(), value.getCidade(), value.getEstado()
        );
    }

    private boolean verifyEndereco(String cep, String cidade, String estado) {
        Endereco consultaCep = consultaCEP(cep);

        return Stream.of(
                removeEspeciais(consultaCep.localidade).equalsIgnoreCase(removeEspeciais(cidade)),
                removeEspeciais(consultaCep.uf).equalsIgnoreCase(removeEspeciais(estado))
        ).allMatch(valor -> valor.equals(true));
    }

    private Endereco consultaCEP(String cep) {
        return cliente.getForEntity(
                "https://viacep.com.br/ws/"+cep+"/json/", Endereco.class
        ).getBody();
    }

    private record Endereco (String localidade, String uf) { }
}
