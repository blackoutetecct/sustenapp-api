package sustenapp_api.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.ConsumoDto;
import sustenapp_api.model.persist.DispositivoModel;
import sustenapp_api.model.persist.RecursoModel;
import sustenapp_api.repository.DispositivoRepository;
import sustenapp_api.repository.RecursoRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsumoService {
    private final RecursoRepository recursoRepository;
    private final DispositivoRepository dispositivoRepository;

    public void save(@Valid ConsumoDto consumo) {
        saveRecurso(consumo);
        saveDispositivo(consumo);
    }

    private void saveRecurso(ConsumoDto consumo) {
        if(existsRecurso(consumo.getModalidade())){
            RecursoModel recurso =  recursoRepository.findById(consumo.getModalidade()).get();
            recurso.setConsumo(recurso.getConsumo() + consumo.getConsumo());
            recursoRepository.save(recurso);
        }
    }

    private void saveDispositivo(ConsumoDto consumo) {
        if(existsDispositivo(consumo.getModalidade())){
            DispositivoModel dispositivo =  dispositivoRepository.findById(consumo.getModalidade()).get();
            dispositivo.setConsumo(dispositivo.getConsumo() + consumo.getConsumo());
            dispositivoRepository.save(dispositivo);
        }
    }

    private boolean existsRecurso(UUID recurso) {
        return recursoRepository.existsById(recurso);
    }

    private boolean existsDispositivo(UUID dispositivo) {
        return dispositivoRepository.existsById(dispositivo);
    }
}
