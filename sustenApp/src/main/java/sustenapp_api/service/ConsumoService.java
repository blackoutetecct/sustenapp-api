package sustenapp_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.ConsumoDto;
import sustenapp_api.model.persist.DispositivoModel;
import sustenapp_api.model.persist.EletricidadeModel;
import sustenapp_api.model.persist.HidricidadeModel;
import sustenapp_api.repository.DispositivoRepository;
import sustenapp_api.repository.EletricidadeRepository;
import sustenapp_api.repository.HidricidadeRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsumoService {
    private final HidricidadeRepository hidricidadeRepository;
    private final EletricidadeRepository eletricidadeRepository;
    private final DispositivoRepository dispositivoRepository;

    public void save(ConsumoDto consumo) {
        saveHidricidade(consumo);
        saveEletricidade(consumo);
        saveDispositivo(consumo);
    }

    private void saveHidricidade(ConsumoDto consumo) {
        if(existsHidricidade(consumo.getModalidade())){
            HidricidadeModel hidricidade =  hidricidadeRepository.findById(consumo.getModalidade()).get();
            hidricidade.setConsumo(hidricidade.getConsumo() + consumo.getConsumo());
            hidricidadeRepository.save(hidricidade);
        }
    }

    private void saveEletricidade(ConsumoDto consumo) {
        if(existsEletricidade(consumo.getModalidade())){
            EletricidadeModel eletricidade =  eletricidadeRepository.findById(consumo.getModalidade()).get();
            eletricidade.setConsumo(eletricidade.getConsumo() + consumo.getConsumo());
            eletricidadeRepository.save(eletricidade);
        }
    }

    private void saveDispositivo(ConsumoDto consumo) {
        if(existsDispositivo(consumo.getModalidade())){
            DispositivoModel dispositivo =  dispositivoRepository.findById(consumo.getModalidade()).get();
            dispositivo.setConsumo(dispositivo.getConsumo() + consumo.getConsumo());
            dispositivoRepository.save(dispositivo);
        }
    }

    private boolean existsHidricidade(UUID hidricidade) {
        return hidricidadeRepository.existsById(hidricidade);
    }

    private boolean existsEletricidade(UUID eletricidade) {
        return eletricidadeRepository.existsById(eletricidade);
    }

    private boolean existsDispositivo(UUID dispositivo) {
        return dispositivoRepository.existsById(dispositivo);
    }
}
