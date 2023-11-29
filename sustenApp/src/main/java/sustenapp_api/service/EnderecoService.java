package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.rule.Validation;
import sustenapp_api.component.validation.*;
import sustenapp_api.dto.POST.EnderecoDto;
import sustenapp_api.dto.PUT.EnderecoPutDto;
import sustenapp_api.exception.BadRequestException;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.exception.NotFoundException;
import sustenapp_api.mapper.EnderecoMapper;
import sustenapp_api.model.persist.EnderecoModel;
import sustenapp_api.repository.EnderecoRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EnderecoService implements Validation<EnderecoDto, EnderecoPutDto> {
    private final EnderecoRepository enderecoRepository;
    private final UsuarioExists usuarioValidation;
    private final EnderecoExists enderecoExists;
    private final EnderecoValid enderecoValid;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public EnderecoModel save(EnderecoDto endereco){
        validatedPost(endereco);
        return enderecoRepository.save(EnderecoMapper.toMapper(endereco));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID endereco){
        try {
            enderecoRepository.deleteById(endereco);
        } catch (Exception ignored) { }
    }

    public EnderecoModel update(EnderecoPutDto endereco){
        validatedPut(endereco);
        return enderecoRepository.save(EnderecoMapper.toMapper(endereco, findById(endereco.getId())));
    }

    public EnderecoModel findById(UUID endereco){
        return enderecoRepository.findById(endereco).orElseThrow(
                () -> new NotFoundException("ENDERECO")
        );
    }

    public List<EnderecoModel> listAllByUsuario(UUID usuario){
        return enderecoRepository.findAllByUsuario(usuario).orElseThrow(
                () -> new NotFoundException("ENDERECO")
        );
    }

    @Override
    public boolean validatePost(EnderecoDto value) {
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
                usuarioValidation.isValid(value.getUsuario()),
                enderecoValid.isValid(value)
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validatedPost(EnderecoDto value) {
        if(!validatePost(value))
            throw new BadRequestException("ENDERECO");
    }

    @Override
    public boolean validatePut(EnderecoPutDto value) {
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
                NotNull.isValid(value.getId()),
                enderecoValid.isValid(value),
                enderecoExists.isValid(value.getId())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validatedPut(EnderecoPutDto value) {
        if(!validatePut(value))
            throw new BadRequestException("ENDERECO");
    }
}
