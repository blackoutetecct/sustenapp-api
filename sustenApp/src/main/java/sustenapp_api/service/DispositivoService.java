package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.rule.Validation;
import sustenapp_api.component.validation.*;
import sustenapp_api.dto.POST.DispositivoDto;
import sustenapp_api.dto.PUT.DispositivoPutDto;
import sustenapp_api.exception.BadRequestException;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.exception.NotFoundException;
import sustenapp_api.mapper.DispositivoMapper;
import sustenapp_api.model.persist.DispositivoModel;
import sustenapp_api.repository.DispositivoRepository;

import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DispositivoService implements Validation<DispositivoDto, DispositivoPutDto> {
    private final DispositivoRepository dispositivoRepository;
    private final ComodoExists comodoExists;
    private final DispositivoExists dispositivoExists;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public DispositivoModel save(@Valid DispositivoDto dispositivo){
        validatedPost(dispositivo);
        return dispositivoRepository.save(new DispositivoMapper().toMapper(dispositivo));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID dispositivo){
        dispositivoRepository.deleteById(dispositivo);
    }

    public DispositivoModel update(DispositivoPutDto dispositivo){
        validatedPut(dispositivo);
        return dispositivoRepository.save(new DispositivoMapper().toMapper(dispositivo, findById(dispositivo.getId())));
    }

    public DispositivoModel findById(UUID dispositivo){
        return dispositivoRepository.findById(dispositivo).orElseThrow(
                () -> new NotFoundException("DISPOSITIVO")
        );
    }

    @Override
    public boolean validatePost(DispositivoDto value) {
        return Stream.of(
                NotNull.isValid(value.getNome()),
                NotNull.isValid(value.getPorta()),
                Positive.isValid(value.getPorta()),
                NotNull.isValid(value.getComodo()),
                comodoExists.isValid(value.getComodo())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validatedPost(DispositivoDto value) {
        if(!validatePost(value))
            throw new BadRequestException("DISPOSITIVO");
    }

    @Override
    public boolean validatePut(DispositivoPutDto value) {
        return Stream.of(
                NotNull.isValid(value.getId()),
                NotNull.isValid(value.getNome()),
                NotNull.isValid(value.getPorta()),
                NotNull.isValid(value.getComodo()),
                NotEmpty.isValid(value.getNome()),
                comodoExists.isValid(value.getComodo()),
                dispositivoExists.isValid(value.getId())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validatedPut(DispositivoPutDto value) {
        if(!validatePut(value))
            throw new BadRequestException("DISPOSITIVO");
    }
}
