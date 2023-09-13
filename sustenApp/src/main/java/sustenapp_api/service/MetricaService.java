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
    private final RecursoRepository recursoRepository;

    public MetricaModel getMetrica() {
        return MetricaModel
                .builder()
                .numeroResidencias(usuarioRepository.count())
                .numeroDispositivos(dispositivoRepository.count())
                .numeroHidricoRenovavel(recursoRepository.getQuantidadeHidricidadeRenovavel())
                .volumeEletricoAdministrado(recursoRepository.getVolumeEletricidade().orElse(0.0))
                .volumeHidricoAdministrado(recursoRepository.getVolumeHidricidade().orElse(0.0))
                .volumeHidricoRenovavel(recursoRepository.getVolumeHidricidadeRenovavel().orElse(0.0))
                .valorHidricoEconomizado(
                        recursoRepository.getVolumeHidricidadeRenovavel().orElse(0.0) * tarifaRepository.getMediaTarifa().orElse(0.0)
                )
                .build();
    }
}