package sustenapp_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.ConsumoDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.model.DispositivoModel;
import sustenapp_api.model.EletricidadeModel;
import sustenapp_api.model.HidricidadeModel;
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
        verifyHidricidadeAndEletricidadeAndDispositivo(consumo.getModalidade());

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

        if(existsDispositivo(consumo.getModalidade())){
            DispositivoModel dispositivo =  dispositivoRepository.findById(consumo.getModalidade()).get();
            dispositivo.setConsumo(dispositivo.getConsumo() + consumo.getConsumo());
            dispositivoRepository.save(dispositivo);
        }
    }

    private void verifyHidricidadeAndEletricidadeAndDispositivo(UUID modalidade) {
        if(!existsEletricidade(modalidade) && !existsHidricidade(modalidade) && !existsDispositivo(modalidade))
            throw new ExceptionGeneric("", "", 404);
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