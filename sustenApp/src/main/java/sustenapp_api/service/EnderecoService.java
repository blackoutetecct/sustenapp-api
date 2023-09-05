package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.rule.Validation;
import sustenapp_api.component.validation.NotEmpty;
import sustenapp_api.component.validation.NotNull;
import sustenapp_api.component.validation.UsuarioValidation;
import sustenapp_api.dto.POST.ComodoDto;
import sustenapp_api.dto.POST.EnderecoDto;
import sustenapp_api.dto.PUT.EnderecoPutDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.EnderecoMapper;
import sustenapp_api.model.persist.EnderecoModel;
import sustenapp_api.repository.EnderecoRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EnderecoService implements Validation<EnderecoDto> {
    private final EnderecoRepository enderecoRepository;
    private final UsuarioValidation usuarioValidation;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public EnderecoModel save(@Valid EnderecoDto endereco){
        validated(endereco);
        return enderecoRepository.save(new EnderecoMapper().toMapper(endereco));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID endereco){
        enderecoRepository.deleteById(endereco);
    }

    public EnderecoModel update(@Valid EnderecoPutDto endereco){
        return enderecoRepository.save(new EnderecoMapper().toMapper(endereco, findById(endereco.getId())));
    }

    public EnderecoModel findById(UUID endereco){
        return enderecoRepository.findById(endereco).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<EnderecoModel> listAllByUsuario(UUID usuario){
        return enderecoRepository.findAllByUsuario(usuario).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    @Override
    public boolean validate(EnderecoDto value) {
        return Stream.of(
                NotNull.isValid(value.getCep()),
                NotEmpty.isValid(value.getCep()),
                NotNull.isValid(value.getEstado()),
                NotEmpty.isValid(value.getEstado()),
                NotNull.isValid(value.getCidade()),
                NotEmpty.isValid(value.getCidade()),
                NotNull.isValid(value.getLogradouro()),
                NotEmpty.isValid(value.getLogradouro()),
                NotNull.isValid(value.getComplemento()),
                NotEmpty.isValid(value.getComplemento()),
                NotNull.isValid(value.getUsuario()),
                usuarioValidation.isValid(value.getUsuario())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validated(EnderecoDto value) {
        if(!validate(value))
            throw new ExceptionGeneric("", "", 404);
    }
}
