package sustenapp_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.HidricidadeDto;
import sustenapp_api.model.HidricidadeModel;
import sustenapp_api.service.HidricidadeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hidricidade")
@RequiredArgsConstructor
public class HidricidadeController {
    private final HidricidadeService hidricidadeService;

    @PostMapping("/save")
    public ResponseEntity<HidricidadeModel> save(@RequestBody @Valid HidricidadeDto hidricidade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hidricidadeService.save(hidricidade));
    }

    @PutMapping("")
    public ResponseEntity<HidricidadeModel> update(@RequestBody HidricidadeModel hidricidade) {
        return ResponseEntity.status(HttpStatus.OK).body(hidricidadeService.update(hidricidade));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        hidricidadeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("")
    public ResponseEntity<HidricidadeModel> findById(@RequestParam UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(hidricidadeService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HidricidadeModel>> listAllByUsuario(@RequestParam UUID usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(hidricidadeService.listAllByUsuario(usuario));
    }
}
