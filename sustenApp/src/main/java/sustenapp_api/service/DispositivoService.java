package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.rule.Validation;
import sustenapp_api.component.validation.ComodoValidation;
import sustenapp_api.component.validation.NotNull;
import sustenapp_api.component.validation.Positive;
import sustenapp_api.component.validation.PositiveOrZero;
import sustenapp_api.dto.POST.ComodoDto;
import sustenapp_api.dto.POST.DispositivoDto;
import sustenapp_api.dto.PUT.DispositivoPutDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.DispositivoMapper;
import sustenapp_api.model.persist.DispositivoModel;
import sustenapp_api.repository.DispositivoRepository;

import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DispositivoService implements Validation<DispositivoDto> {
    private final DispositivoRepository dispositivoRepository;
    private final ComodoValidation comodoValidation;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public DispositivoModel save(@Valid DispositivoDto dispositivo){
        validated(dispositivo);
        return dispositivoRepository.save(new DispositivoMapper().toMapper(dispositivo));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID dispositivo){
        dispositivoRepository.deleteById(dispositivo);
    }

    public DispositivoModel update(@Valid DispositivoPutDto dispositivo){
        return dispositivoRepository.save(new DispositivoMapper().toMapper(dispositivo, findById(dispositivo.getId())));
    }

    public DispositivoModel findById(UUID dispositivo){
        return dispositivoRepository.findById(dispositivo).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    @Override
    public boolean validate(DispositivoDto value) {
        return Stream.of(
                NotNull.isValid(value.getNome()),
                NotNull.isValid(value.getPorta()),
                Positive.isValid(value.getPorta()),
                NotNull.isValid(value.getComodo()),
                comodoValidation.isValid(value.getComodo())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validated(DispositivoDto value) {
        if(!validate(value))
            new ExceptionGeneric("", "", 500);
    }
}