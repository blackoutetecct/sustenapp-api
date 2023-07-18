package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sustenapp_api.component.dependency.DateDependency;
import sustenapp_api.dto.POST.TarifaDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.TarifaMapper;
import sustenapp_api.model.persist.TarifaModel;
import sustenapp_api.repository.TarifaRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TarifaService {
    private final TarifaRepository tarifaRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public TarifaModel save(@Valid TarifaDto tarifaDto){
        var tarifa = new TarifaMapper().toMapper(tarifaDto);
        verifyDate(tarifa);

        return tarifaRepository.save(tarifa);
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID tarifa){
        tarifaRepository.deleteById(tarifa);
    }

    @Scheduled(cron = "* 01 00 1 * *")
    public void factory() {
        tarifaRepository.save(clone(findLast()));
    }

    public TarifaModel findById(UUID tarifa){
        return tarifaRepository.findById(tarifa).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<TarifaModel> listAll(){
        return tarifaRepository.findAll();
    }

    public TarifaModel findLast() {
        return tarifaRepository.getTopByData().orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    private TarifaModel clone(TarifaModel tarifa) {
        tarifa.setId(null);
        tarifa.setData(DateDependency.getDate());

        return tarifa;
    }

    private void verifyDate(TarifaModel tarifa) {
        if(checkDate(tarifa))
            throw new ExceptionGeneric("", "", 404);
    }

    private boolean checkDate(TarifaModel tarifa) {
        return DateDependency.checkDate(tarifa.getData());
    }
}
