package sustenapp_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.TarifaDto;
import sustenapp_api.model.persist.TarifaModel;
import sustenapp_api.service.TarifaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tarifa")
@RequiredArgsConstructor
public class TarifaController {
    private final TarifaService tarifaService;

    @PostMapping("")
    public ResponseEntity<TarifaModel> save(@RequestBody TarifaDto tarifa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarifaService.save(tarifa));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        tarifaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("")
    public ResponseEntity<TarifaModel> findById(@RequestParam UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(tarifaService.findById(id));
    }

    @GetMapping("/last")
    public ResponseEntity<TarifaModel> findLaste() {
        return ResponseEntity.status(HttpStatus.OK).body(tarifaService.findLast());
    }

    @GetMapping("/all")
    public ResponseEntity<List<TarifaModel>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(tarifaService.listAll());
    }
}
