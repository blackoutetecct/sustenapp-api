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
import sustenapp_api.dto.PUT.ComodoPutDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.ComodoMapper;
import sustenapp_api.model.persist.ComodoModel;
import sustenapp_api.repository.ComodoRepository;
import sustenapp_api.repository.DispositivoRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ComodoService implements Validation<ComodoDto> {
    private final ComodoRepository comodoRepository;
    private final UsuarioValidation usuarioValidation;
    private final DispositivoRepository dispositivoRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public ComodoModel save(ComodoDto comodo){
        validated(comodo);
        return comodoRepository.save(new ComodoMapper().toMapper(comodo));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID comodo){
        comodoRepository.deleteById(comodo);
    }

    public ComodoModel update(@Valid ComodoPutDto comodo){
       return comodoRepository.save(new ComodoMapper().toMapper(comodo, findById(comodo.getId())));
    }

    public ComodoModel findById(UUID comodo){
        return comodoRepository.findById(comodo).map(this::getFull).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<ComodoModel> listAllByUsuario(UUID usuario){
        return comodoRepository.findAllByUsuario(usuario).orElseThrow(
                () -> new ExceptionGeneric("", "", 400)
        );
    }

    public ComodoModel getFull(ComodoModel comodo) {
        comodo.setDispositivos(
                dispositivoRepository.findAllByComodo(comodo.getId()).orElseThrow(
                        () -> new ExceptionGeneric("", "", 400)
                )
        );

        return comodo;
    }

    @Override
    public boolean validate(ComodoDto value) {
        return Stream.of(
                NotNull.isValid(value.getNome()),
                NotEmpty.isValid(value.getNome()),
                NotNull.isValid(value.getUsuario()),
                usuarioValidation.isValid(value.getUsuario())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validated(ComodoDto value) {
        if(!validate(value))
            throw new ExceptionGeneric("", "", 404);
    }
}
