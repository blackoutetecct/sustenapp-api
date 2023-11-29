package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.rule.Validation;
import sustenapp_api.component.validation.NotNull;
import sustenapp_api.component.validation.PreferenciaExists;
import sustenapp_api.component.validation.UsuarioExists;
import sustenapp_api.dto.POST.PreferenciaDto;
import sustenapp_api.dto.PUT.PreferenciaPutDto;
import sustenapp_api.exception.BadRequestException;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.exception.NotFoundException;
import sustenapp_api.mapper.PreferenciaMapper;
import sustenapp_api.model.persist.PreferenciaModel;
import sustenapp_api.repository.PreferenciaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PreferenciaService implements Validation<PreferenciaDto, PreferenciaPutDto> {
    private final PreferenciaRepository preferenciaRepository;
    private final UsuarioExists usuarioValidation;
    private final PreferenciaExists preferenciaExists;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public PreferenciaModel save(PreferenciaDto preferencia){
        validatedPost(preferencia);
        return preferenciaRepository.save(PreferenciaMapper.toMapper(preferencia));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID preferencia) {
        try {
            preferenciaRepository.deleteById(preferencia);
        } catch (Exception ignored) { }
    }

    public PreferenciaModel update(PreferenciaPutDto preferencia){
        validatePut(preferencia);
        return preferenciaRepository.save(PreferenciaMapper.toMapper(preferencia, findById(preferencia.getId())));
    }

    public PreferenciaModel findById(UUID preferencia){
        return preferenciaRepository.findById(preferencia).orElseThrow(
                () -> new NotFoundException("PREFERENCIA")
        );
    }

    public List<PreferenciaModel> listAll(){
        return preferenciaRepository.findAll();
    }

    @Override
    public boolean validatePost(PreferenciaDto value) {
        return Stream.of(
                NotNull.isValid(value.getUsuario()),
                usuarioValidation.isValid(value.getUsuario())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validatedPost(PreferenciaDto value) {
        if(!validatePost(value))
            throw new BadRequestException("PREFERENCIA");
    }

    @Override
    public boolean validatePut(PreferenciaPutDto value) {
        return Stream.of(
                NotNull.isValid(value.getId()),
                preferenciaExists.isValid(value.getId())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validatedPut(PreferenciaPutDto value) {
        if(!validatePut(value))
            throw new BadRequestException("PREFERENCIA");
    }
}