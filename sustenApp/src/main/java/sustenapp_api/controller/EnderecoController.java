package sustenapp_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.EnderecoDto;
import sustenapp_api.model.persist.EnderecoModel;
import sustenapp_api.service.EnderecoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService enderecoService;

    @PostMapping("")
    public ResponseEntity<EnderecoModel> save(@RequestBody EnderecoDto endereco) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(endereco));
    }

    @PutMapping("")
    public ResponseEntity<EnderecoModel> update(@RequestBody EnderecoModel endereco) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.update(endereco));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        enderecoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("")
    public ResponseEntity<EnderecoModel> findById(@RequestParam UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EnderecoModel>> listAllByUsuario(UUID usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.listAllByUsuario(usuario));
    }
}
