package sustenapp_api.integration.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import sustenapp_api.dto.POST.TelefoneDto;
import sustenapp_api.integration.util.TelefoneUtil;
import sustenapp_api.integration.util.UsuarioUtil;
import sustenapp_api.repository.TelefoneRepository;
import sustenapp_api.repository.UsuarioRepository;
import sustenapp_api.service.TelefoneService;
import sustenapp_api.service.UsuarioService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static sustenapp_api.integration.util.TelefoneUtil.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class TelefoneIT {

    @Autowired private TelefoneService service;
    @Autowired private TelefoneRepository repository;
    @Autowired private UsuarioService usuarioService;
    @Autowired private UsuarioRepository usuarioRepository;

    private UUID usuario;

    @BeforeEach
    private void setUp() {
        saveUsuario();
    }

    @AfterEach
    private void reset() {
        repository.deleteAll();
        usuarioRepository.deleteAll();
    }

    @Test
    @DisplayName("Testes de Cobertura e Validacao do Metodo Save")
    public void save() {
        var atual = service.save(factoryDto(usuario));
        var esperado = factoryDto(usuario);

        assertAll(
                () -> assertEquals(esperado.getNumero(), atual.getNumero()),
                () -> assertEquals(esperado.getUsuario(), atual.getUsuario()),

                () -> assertThrows(
                        // @NotNull e @NotEmpty - OBSERVACAO
                        Exception.class, () -> service.save(TelefoneDto.builder().build())
                ),
                () -> assertThrows(
                        // @UsuarioVerify - OBSERVACAO
                        Exception.class, () -> service.save(TelefoneUtil.factoryDto(UUID.fromString(USUARIO), NUMERO))
                )
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validação do Metodo Delete")
    public void delete() {
        assertDoesNotThrow(() -> service.delete(service.save(factoryDto(usuario)).getId()));
    }

    @Test
    @DisplayName("Testes de Cobertura e Validação do Metodo FindById")
    public void findById() {
        var atual = service.save(factoryDto(usuario));

        assertAll(
                () -> assertEquals(atual, service.findById(atual.getId())),
                () -> assertThrows(
                        Exception.class, () -> service.findById(UUID.fromString(ID))
                )
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validacao do Metodo Update")
    public void update() {
        var atual = service.save(factoryDto(usuario));
        var modificado = service.update(factoryPutDto(atual.getId()));

        assertAll(
                () -> assertNotEquals(atual, modificado),
                () -> assertNotEquals(atual.getNumero(), modificado.getNumero()),
                () -> assertEquals(atual.getId(), modificado.getId()),

                () -> assertThrows(
                        Exception.class, () -> {
                            service.update(factoryPutDto(UUID.fromString(ID)));
                        }
                )
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validação do Metodo listAllByUsuario")
    public void listAllByUsuario() {
        var telefone = service.save(TelefoneUtil.factoryDto(UUID.fromString(USUARIO), NUMERO));

        assertEquals(
                List.of(telefone), service.listAllByUsuario(UUID.fromString(USUARIO))
        );
    }

    private void saveUsuario() {
        usuario = usuarioService.save(UsuarioUtil.factoryDto()).getId();
    }
}
