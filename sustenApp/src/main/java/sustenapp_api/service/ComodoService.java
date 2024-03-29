package sustenapp_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.rule.Validation;
import sustenapp_api.component.validation.*;
import sustenapp_api.dto.POST.ComodoDto;
import sustenapp_api.dto.PUT.ComodoPutDto;
import sustenapp_api.exception.BadRequestException;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.exception.NotFoundException;
import sustenapp_api.mapper.ComodoMapper;
import sustenapp_api.model.persist.ComodoModel;
import sustenapp_api.repository.ComodoRepository;
import sustenapp_api.repository.DispositivoRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ComodoService implements Validation<ComodoDto, ComodoPutDto> {
    private final ComodoRepository comodoRepository;
    private final UsuarioExists usuarioValidation;
    private final DispositivoRepository dispositivoRepository;
    private final ComodoExists comodoExists;
    private final NomeComodoNotExists nomeComodoExists;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public ComodoModel save(ComodoDto comodo){
        validatedPost(comodo);
        return comodoRepository.save(ComodoMapper.toMapper(comodo));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID comodo){
        try {
            comodoRepository.deleteById(comodo);
        } catch (Exception ignored) { }
    }

    public ComodoModel update(ComodoPutDto comodo) {
        validatedPut(comodo);
       return comodoRepository.save(ComodoMapper.toMapper(comodo, findById(comodo.getId())));
    }

    public ComodoModel findById(UUID comodo){
        return comodoRepository.findById(comodo).map(this::getFull).orElseThrow(
                () -> new NotFoundException("COMODO")
        );
    }

    public List<ComodoModel> listAllByUsuario(UUID usuario){
        return comodoRepository.findAllByUsuario(usuario)
                .orElseThrow(() -> new NotFoundException("COMODO"))
                .stream().map(this::getFull).toList();
    }

    private ComodoModel getFull(ComodoModel comodo) {
        comodo.setDispositivos(
                dispositivoRepository.findAllByComodo(comodo.getId()).orElseThrow(
                        () -> new NotFoundException("COMODO")
                )
        );

        return comodo;
    }

    @Override
    public boolean validatePost(ComodoDto value) {
        return Stream.of(
                NotNull.isValid(value.getNome()),
                NotEmpty.isValid(value.getNome()),
                NotNull.isValid(value.getUsuario()),
                usuarioValidation.isValid(value.getUsuario()),
                nomeComodoExists.isValid(value.getUsuario(), value.getNome())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validatedPost(ComodoDto value) {
        if(!validatePost(value))
            throw new BadRequestException("COMODO");
    }

    @Override
    public boolean validatePut(ComodoPutDto value) {
        return Stream.of(
                NotNull.isValid(value.getId()),
                NotNull.isValid(value.getNome()),
                NotEmpty.isValid(value.getNome()),
                comodoExists.isValid(value.getId()),
                nomeComodoExists.isValidPut(value.getId(), value.getNome())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validatedPut(ComodoPutDto value) {
        if(!validatePut(value))
            throw new BadRequestException("COMODO");
    }
}
