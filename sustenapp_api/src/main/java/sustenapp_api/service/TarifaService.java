package sustenapp_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.dependency.DateDependency;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.model.TarifaModel;
import sustenapp_api.repository.TarifaRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TarifaService {
    private final TarifaRepository tarifaRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public TarifaModel save(TarifaModel tarifa){
        return tarifaRepository.save(setTime(tarifa));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID tarifa){
        tarifaRepository.deleteById(tarifa);
    }

    public TarifaModel findById(UUID tarifa){
        return tarifaRepository.findById(tarifa).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<TarifaModel> listAll(){
        return tarifaRepository.findAll();
    }

    private TarifaModel setTime(TarifaModel tarifa) {
        tarifa.setData(DateDependency.getTime());
        return tarifa;
    }
}
