package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.POST.DispositivoDto;
import sustenapp_api.dto.PUT.DispositivoPutDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.DispositivoMapper;
import sustenapp_api.model.persist.DispositivoModel;
import sustenapp_api.repository.DispositivoRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DispositivoService {
    private final DispositivoRepository dispositivoRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public DispositivoModel save(@Valid DispositivoDto dispositivo){
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
}