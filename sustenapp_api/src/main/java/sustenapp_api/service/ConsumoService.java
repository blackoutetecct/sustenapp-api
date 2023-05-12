package sustenapp_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.ConsumoDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.model.EletricidadeModel;
import sustenapp_api.model.HidricidadeModel;
import sustenapp_api.repository.EletricidadeRepository;
import sustenapp_api.repository.HidricidadeRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsumoService {
    private final HidricidadeRepository hidricidadeRepository;
    private final EletricidadeRepository eletricidadeRepository;

    public void save(ConsumoDto consumo) {
        verifyHidricidadeAndEletricidade(consumo.getModalidade());

        if(existsHidricidade(consumo.getModalidade())){
            HidricidadeModel hidricidade =  hidricidadeRepository.findById(consumo.getModalidade()).get();
            hidricidade.setConsumo(hidricidade.getConsumo() + consumo.getConsumo());
            hidricidadeRepository.save(hidricidade);
        }

        if(existsEletricidade(consumo.getModalidade())){
            EletricidadeModel eletricidade =  eletricidadeRepository.findById(consumo.getModalidade()).get();
            eletricidade.setConsumo(eletricidade.getConsumo() + consumo.getConsumo());
            eletricidadeRepository.save(eletricidade);
        }
    }

    private void verifyHidricidadeAndEletricidade(UUID modalidade) {
        if(!existsEletricidade(modalidade) && !existsHidricidade(modalidade))
            throw new ExceptionGeneric("", "", 404);
    }

    private boolean existsHidricidade(UUID hidricidade) {
        return hidricidadeRepository.existsById(hidricidade);
    }

    private boolean existsEletricidade(UUID eletricidade) {
        return eletricidadeRepository.existsById(eletricidade);
    }
}
