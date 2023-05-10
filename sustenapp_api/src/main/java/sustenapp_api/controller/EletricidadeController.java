package sustenapp_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.EletricidadeDto;
import sustenapp_api.model.EletricidadeModel;
import sustenapp_api.service.EletricidadeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eletricidade")
@RequiredArgsConstructor
public class EletricidadeController {
    private final EletricidadeService eletricidadeService;

    @PostMapping("/save")
    public ResponseEntity<EletricidadeModel> save(@RequestBody @Valid EletricidadeDto eletricidade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eletricidadeService.save(eletricidade));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        eletricidadeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("")
    public ResponseEntity<EletricidadeModel> findById(@RequestParam UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(eletricidadeService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EletricidadeModel>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(eletricidadeService.listAll());
    }
}
