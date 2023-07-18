package sustenapp_api.component.constraint.classVerify;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.RestTemplate;
import sustenapp_api.component.constraint.annotation.EnderecoVerify;
import sustenapp_api.dto.POST.EnderecoDto;

import java.util.stream.Stream;

import static sustenapp_api.component.treatment.StringTreatment.removeEspeciais;

@RequiredArgsConstructor
public class EnderecoVerifyClass implements ConstraintValidator<EnderecoVerify, EnderecoDto> {
    private final RestTemplate cliente;

    @Override
    public boolean isValid(EnderecoDto value, ConstraintValidatorContext constraintValidatorContext) {
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

    @Retryable(value = Exception.class, maxAttempts = 4, backoff = @Backoff(delay = 2000))
    private Endereco consultaCEP(String cep) {
        return cliente.getForEntity(
                "https://viacep.com.br/ws/"+cep+"/json/", Endereco.class
        ).getBody();
    }

    private record Endereco(String logradouro, String localidade, String uf) { }
}