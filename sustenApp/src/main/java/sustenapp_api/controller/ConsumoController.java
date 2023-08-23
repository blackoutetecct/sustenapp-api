package sustenapp_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.POST.ConsumoDto;
import sustenapp_api.service.ConsumoService;

@RestController
@RequestMapping("/consumo")
@RequiredArgsConstructor
public class ConsumoController {
    private final ConsumoService consumoService;

    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody ConsumoDto consumo) {
        consumoService.save(consumo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
