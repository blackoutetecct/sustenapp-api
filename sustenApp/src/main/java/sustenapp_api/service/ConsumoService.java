package sustenapp_api.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.POST.ConsumoDto;
import sustenapp_api.model.persist.RecursoModel;
import sustenapp_api.repository.RecursoRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsumoService {
    private final RecursoRepository recursoRepository;

    public void save(@Valid ConsumoDto consumo) {
        saveRecurso(consumo);
    }

    private void saveRecurso(ConsumoDto consumo) {
        if(existsRecurso(consumo.getRecurso())){
            RecursoModel recurso =  recursoRepository.findById(consumo.getRecurso()).get();
            recurso.setConsumo(recurso.getConsumo() + consumo.getConsumo());
            recursoRepository.save(recurso);
        }
    }

    private boolean existsRecurso(UUID recurso) {
        return recursoRepository.existsById(recurso);
    }
}
