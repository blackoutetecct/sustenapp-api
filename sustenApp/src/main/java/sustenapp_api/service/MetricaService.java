package sustenapp_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.model.metric.MetricaModel;
import sustenapp_api.repository.DispositivoRepository;
import sustenapp_api.repository.EletricidadeRepository;
import sustenapp_api.repository.HidricidadeRepository;
import sustenapp_api.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class MetricaService {
    private final UsuarioRepository usuarioRepository;
    private final DispositivoRepository dispositivoRepository;
    private final EletricidadeRepository eletricidadeRepository;
    private final HidricidadeRepository hidricidadeRepository;

    public MetricaModel getMetrica() {
        return MetricaModel
                .builder()
                .numeroResidencias(usuarioRepository.count())
                .numeroDispositivos(dispositivoRepository.count())
                .volumeEletricoAdministrado(eletricidadeRepository.getVolumeEletricidade())
                .volumeEletricoRenovavel(eletricidadeRepository.getVolumeEletricidadeRenovavel())
                .volumeHidricoAdministrado(hidricidadeRepository.getVolumeHidricidade())
                .volumeHidricoRenovavel(hidricidadeRepository.getVolumeHidricidadeRenovavel())
                .build();
    }
}