package sustenapp_api.model.metric;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetricaModel {
    private long numeroResidencias, numeroDispositivos, numeroHidricoRenovavel;
    private Double
            volumeEletricoAdministrado, volumeHidricoAdministrado,
            volumeHidricoRenovavel, valorHidricoEconomizado;
}
