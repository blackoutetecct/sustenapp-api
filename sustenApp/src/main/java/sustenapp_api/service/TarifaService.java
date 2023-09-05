package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sustenapp_api.component.dependency.DateDependency;
import sustenapp_api.component.rule.Validation;
import sustenapp_api.component.validation.NotEmpty;
import sustenapp_api.component.validation.NotNull;
import sustenapp_api.component.validation.Positive;
import sustenapp_api.component.validation.Size;
import sustenapp_api.dto.POST.ComodoDto;
import sustenapp_api.dto.POST.TarifaDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.TarifaMapper;
import sustenapp_api.model.persist.TarifaModel;
import sustenapp_api.repository.TarifaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TarifaService implements Validation<TarifaDto>  {
    private final TarifaRepository tarifaRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public TarifaModel save(@Valid TarifaDto tarifaDto){
        validated(tarifaDto);
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

    @Override
    public boolean validate(TarifaDto value) {
        return Stream.of(
                NotNull.isValid(value.getPreco()),
                Positive.isValid(value.getPreco()),
                NotNull.isValid(value.getTipo()),
                NotEmpty.isValid(value.getTipo()),
                Size.isValid(value.getTipo(), 7, 8),
                NotNull.isValid(value.getObservacao()),
                NotEmpty.isValid(value.getObservacao())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validated(TarifaDto value) {
        if(!validate(value))
            throw new ExceptionGeneric("", "", 404);
    }
}
