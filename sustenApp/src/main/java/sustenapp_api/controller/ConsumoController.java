package sustenapp_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.ConsumoDto;
import sustenapp_api.dto.EletricidadeDto;
import sustenapp_api.model.EletricidadeModel;
import sustenapp_api.service.ConsumoService;
import sustenapp_api.service.EletricidadeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/consumo")
@RequiredArgsConstructor
public class ConsumoController {
    private final ConsumoService consumoService;

    @PostMapping("")
    public ResponseEntity<EletricidadeModel> save(@RequestBody @Valid ConsumoDto consumo) {
        consumoService.save(consumo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
