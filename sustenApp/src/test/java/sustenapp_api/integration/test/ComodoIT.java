package sustenapp_api.integration.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import sustenapp_api.dto.POST.ComodoDto;
import sustenapp_api.integration.util.ComodoUtil;
import sustenapp_api.integration.util.UsuarioUtil;
import sustenapp_api.repository.ComodoRepository;
import sustenapp_api.repository.UsuarioRepository;
import sustenapp_api.service.ComodoService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static sustenapp_api.integration.util.ComodoUtil.*;
import sustenapp_api.service.UsuarioService;

@SpringBootTest
@AutoConfigureTestDatabase
public class ComodoIT {
    @Autowired private ComodoService service;
    @Autowired private ComodoRepository repository;
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

        assertAll(
               () -> assertEquals(factoryDto(usuario).getNome(), atual.getNome()),
               () -> assertEquals(factoryDto(usuario).getUsuario(), atual.getUsuario()),
               () -> assertThrows(
                       Exception.class, () -> service.save(ComodoDto.builder().build())
               ),
               () -> assertThrows(
                       Exception.class, () -> service.save(ComodoUtil.factoryDto(UUID.fromString(USUARIO), ComodoUtil.NOME))
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
                () -> assertEquals(atual.getId(), service.findById(atual.getId()).getId()),
                () -> assertThrows(
                        Exception.class, () -> service.findById(UUID.fromString(UsuarioUtil.ID))
                )
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validacao do Metodo Update")
    public void update() {
        var atual = service.save(factoryDto(usuario));
        var modificado = service.update(ComodoUtil.factoryPutDto(atual.getId()));

        assertAll(
                () -> assertNotEquals(atual, modificado),
                () -> assertNotEquals(atual.getNome(), modificado.getNome()),
                () -> assertEquals(atual.getUsuario(), modificado.getUsuario()),
                () -> assertDoesNotThrow(() -> service.delete(UUID.fromString(UsuarioUtil.ID)))
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validação do Metodo listAllByUsuario")
    public void listAllByUsuario() {
        var comodo = service.save(ComodoUtil.factoryDto(UUID.fromString(USUARIO), ComodoUtil.NOME));

        assertEquals(
                List.of(comodo), service.listAllByUsuario(UUID.fromString(USUARIO))
        );
    }

    private void saveUsuario() {
        usuario = usuarioService.save(UsuarioUtil.factoryDto()).getId();
    }
}