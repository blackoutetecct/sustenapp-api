package sustenapp_api.integration.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import sustenapp_api.repository.RecursoRepository;
import sustenapp_api.integration.util.UsuarioUtil;
import sustenapp_api.integration.util.TarifaUtil;
import sustenapp_api.repository.TarifaRepository;
import sustenapp_api.repository.UsuarioRepository;
import sustenapp_api.service.RecursoService;
import sustenapp_api.service.TarifaService;
import sustenapp_api.service.UsuarioService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sustenapp_api.integration.util.RecursoUtil.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class RecursoIT {

    @Autowired private RecursoService service;
    @Autowired private RecursoRepository repository;
    @Autowired private UsuarioService usuarioService;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private TarifaService tarifaService;
    @Autowired private TarifaRepository tarifaRepository;

    private UUID usuario, tarifa;

    @BeforeEach
    private void setUp() {
        saveUsuario();
        saveTarifa();
    }

    @AfterEach
    private void reset() {
        repository.deleteAll();
        usuarioRepository.deleteAll();
        tarifaRepository.deleteAll();
    }

    @Test
    @DisplayName("Testes de Cobertura e Validacao do Metodo Save")
    public void save() {
        var atual = service.save(factoryDto(usuario, tarifa));
        var esperado = factoryDto(UUID.fromString(UsuarioUtil.ID), UUID.fromString(TarifaUtil.ID));

        assertAll(
                () -> assertEquals(esperado.getTipo(), atual.getTipo().toString()),
                () -> assertEquals(esperado.isRenovavel(), atual.isRenovavel())
                // RESTANTE(THROWS) -> ACEITA COMODO INEXISTENTE
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validação do Metodo Delete")
    public void delete() {
        assertDoesNotThrow(() -> service.delete(service.save(factoryDto(usuario, tarifa)).getId()));
    }

    @Test
    @DisplayName("Testes de Cobertura e Validação do Metodo FindById")
    public void findById() {
        var atual = service.save(factoryDto(usuario, tarifa));

        assertAll(
                () -> assertEquals(atual.getId(), service.findById(atual.getId()).getId()),
                () -> assertThrows(
                        Exception.class, () -> service.findById(UUID.fromString(ID))
                )
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validacao do Metodo Update")
    public void update() {
        var atual = service.save(factoryDto(usuario, tarifa));
        var modificado = service.update(factoryPutDto(atual.getId()));

        assertAll(
                () -> assertNotEquals(atual, modificado),
                () -> assertEquals(atual.getConsumo(), modificado.getConsumo()),
                () -> assertEquals(atual.getId(), modificado.getId()),
                () -> assertThrows(
                        Exception.class, () -> service.update(factoryPutDto(UUID.fromString(UsuarioUtil.ID)))
                )
        );
    }

    private void saveUsuario() {
        usuario = usuarioService.save(UsuarioUtil.factoryDto()).getId();
    }

    private void saveTarifa() {
        tarifa = tarifaService.save(TarifaUtil.factoryDto()).getId();
    }
}