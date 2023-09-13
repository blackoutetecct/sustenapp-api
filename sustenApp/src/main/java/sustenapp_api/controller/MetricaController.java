package sustenapp_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.model.metric.MetricaModel;
import sustenapp_api.service.MetricaService;

@RestController
@RequestMapping("/metrica")
@RequiredArgsConstructor
public class MetricaController {
    private final MetricaService metricaService;

    @GetMapping("")
    public ResponseEntity<MetricaModel> getMetrica() {
        return ResponseEntity.status(HttpStatus.OK).body(metricaService.getMetrica());
    }
}
