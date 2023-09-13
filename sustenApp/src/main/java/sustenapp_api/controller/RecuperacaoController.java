package sustenapp_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.PUT.RecuperacaoDto;
import sustenapp_api.model.dynamic.RecuperacaoModel;
import sustenapp_api.service.RecuperacaoService;

@RestController
@RequestMapping("/recuperacao")
@RequiredArgsConstructor
public class RecuperacaoController {
    private final RecuperacaoService recuperacaoService;

    @PostMapping("")
    public ResponseEntity<RecuperacaoModel> save(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(recuperacaoService.check(email));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestParam String email) {
        recuperacaoService.delete(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("")
    public ResponseEntity<RecuperacaoModel> findCodigo(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(recuperacaoService.findCodigo(email));
    }

    @PostMapping("/change")
    public ResponseEntity<RecuperacaoModel> change(@RequestBody RecuperacaoDto recuperacao) {
        recuperacaoService.change(recuperacao);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
