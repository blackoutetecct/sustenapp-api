package sustenapp_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.model.metric.MetricaModel;
import sustenapp_api.repository.*;

@Service
@RequiredArgsConstructor
public class MetricaService {
    private final UsuarioRepository usuarioRepository;
    private final TarifaRepository tarifaRepository;
    private final DispositivoRepository dispositivoRepository;
    private final EletricidadeRepository eletricidadeRepository;
    private final HidricidadeRepository hidricidadeRepository;

    public MetricaModel getMetrica() {
        return MetricaModel
                .builder()
                .numeroResidencias(usuarioRepository.count())
                .numeroDispositivos(dispositivoRepository.count())
                .numeroEletricoRenovavel(eletricidadeRepository.getQuantidadeEletricidadeRenovavel())
                .numeroHidricoRenovavel(hidricidadeRepository.getQuantidadeHidricidadeRenovavel())
                .volumeEletricoAdministrado(eletricidadeRepository.getVolumeEletricidade().orElse(0.0))
                .volumeEletricoRenovavel(eletricidadeRepository.getVolumeEletricidadeRenovavel().orElse(0.0))
                .volumeHidricoAdministrado(hidricidadeRepository.getVolumeHidricidade().orElse(0.0))
                .volumeHidricoRenovavel(hidricidadeRepository.getVolumeHidricidadeRenovavel().orElse(0.0))
                .valorEletricoEconomizado(
                        eletricidadeRepository.getVolumeEletricidadeRenovavel().orElse(0.0) * tarifaRepository.getMediaTarifa().orElse(0.0)
                )
                .valorHidricoEconomizado(
                        hidricidadeRepository.getVolumeHidricidadeRenovavel().orElse(0.0) * tarifaRepository.getMediaTarifa().orElse(0.0)
                )
                .build();
    }
}