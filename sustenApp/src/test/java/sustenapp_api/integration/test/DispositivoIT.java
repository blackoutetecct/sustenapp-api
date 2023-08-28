package sustenapp_api.integration.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import sustenapp_api.dto.POST.DispositivoDto;
import sustenapp_api.integration.util.ComodoUtil;
import sustenapp_api.integration.util.UsuarioUtil;
import sustenapp_api.repository.ComodoRepository;
import sustenapp_api.repository.DispositivoRepository;
import sustenapp_api.repository.UsuarioRepository;
import sustenapp_api.service.ComodoService;
import sustenapp_api.service.DispositivoService;
import sustenapp_api.service.UsuarioService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static sustenapp_api.integration.util.DispositivoUtil.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class DispositivoIT {

    @Autowired private DispositivoService service;
    @Autowired private DispositivoRepository repository;
    @Autowired private ComodoService comodoService;
    @Autowired private ComodoRepository comodoRepository;
    @Autowired private UsuarioService usuarioService;
    @Autowired private UsuarioRepository usuarioRepository;

    private UUID comodo;

    @BeforeEach
    private void setUp() {
        saveComodo();
    }

    @AfterEach
    private void reset() {
        repository.deleteAll();
        comodoRepository.deleteAll();
        usuarioRepository.deleteAll();
    }

    @Test
    @DisplayName("Testes de Cobertura e Validacao do Metodo Save")
    public void save() {
        var atual = service.save(factoryDto(comodo));
        var esperado = factoryDto(comodo);

        assertAll(
                () -> assertEquals(esperado.getComodo(), atual.getComodo()),
                () -> assertEquals(esperado.getPorta(), atual.getPorta()),
                () -> assertEquals(esperado.getNome(), atual.getNome()),

                () -> assertThrows(
                        // NotNull NotEmpty
                        Exception.class, () -> service.save(DispositivoDto.builder().build())
                ),
                () -> assertThrows(
                        // NotNull
                        Exception.class, () -> service.save(factoryDto(comodo, null, PORTA))
                ),
                () -> assertThrows(
                        // PositivoOrZero
                        Exception.class, () -> service.save(factoryDto(comodo, NOME, 0))
                ),
                () -> assertThrows(
                        // ComodoVerify
                        Exception.class, () -> service.save(factoryDto(UUID.fromString(ID), NOME, PORTA))
                )
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validação do Metodo Delete")
    public void delete() {
        assertDoesNotThrow(() -> service.delete(service.save(factoryDto(comodo)).getId()));
    }

    @Test
    @DisplayName("Testes de Cobertura e Validação do Metodo FindById")
    public void findById() {
        var atual = service.save(factoryDto(comodo));

        assertAll(
                () -> assertEquals(atual.getId(), service.findById(atual.getId()).getId()),
                () -> assertThrows(
                        Exception.class, () -> service.findById(UUID.fromString(ComodoUtil.ID))
                )
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validacao do Metodo Update")
    public void update() {
        var atual = service.save(factoryDto(comodo));
        var modificado = service.update(factoryPutDto(atual.getId()));

        assertAll(
                () -> assertNotEquals(atual, modificado),
                () -> assertNotEquals(atual.getNome(), modificado.getNome()),
                () -> assertEquals(atual.getId(), modificado.getId()),

                () -> assertThrows(
                        Exception.class, () -> service.update(factoryPutDto(UUID.fromString(ComodoUtil.ID)))
                )
        );
    }

    private void saveComodo() {
        UUID usuario = usuarioService.save(UsuarioUtil.factoryDto()).getId();
        comodo = comodoService.save(ComodoUtil.factoryDto(usuario)).getId();
    }
}