package sustenapp_api.integration.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import sustenapp_api.dto.POST.EnderecoDto;
import sustenapp_api.integration.util.EnderecoUtil;

import sustenapp_api.integration.util.UsuarioUtil;
import sustenapp_api.repository.EnderecoRepository;
import sustenapp_api.repository.UsuarioRepository;
import sustenapp_api.service.EnderecoService;
import sustenapp_api.service.UsuarioService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static sustenapp_api.integration.util.ComodoUtil.USUARIO;
import static sustenapp_api.integration.util.EnderecoUtil.*;



@SpringBootTest
@AutoConfigureTestDatabase
public class EnderecoIT {

    @Autowired private EnderecoService service;
    @Autowired private EnderecoRepository repository;
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
    @DisplayName("Testes de Cobertura e Validação do Metodo Save")
     public void save(){
        var atual = service.save(factoryDto(usuario));
        var esperado = factoryDto(usuario);

        assertAll(
                () -> assertEquals(esperado.getUsuario(), atual.getUsuario()),
                () -> assertEquals(esperado.getCep(), atual.getCep()),
                () -> assertEquals(esperado.getCidade(), atual.getCidade()),
                () -> assertEquals(esperado.getEstado(), atual.getEstado()),
                () -> assertEquals(esperado.getLogradouro(), atual.getLogradouro()),
                () -> assertEquals(esperado.getComplemento(), atual.getComplemento()),

                () -> assertThrows(
                        // @NotNull - OBSERVACAO
                        Exception.class, () -> service.save(EnderecoDto.builder().build())
                ),
                () -> assertThrows(
                        // @NotEmpty - OBSERVACAO
                        Exception.class, () -> service.save(EnderecoUtil.factoryDto(UUID.fromString(USUARIO), LOGRADOURO, COMPLEMENTO, "", CIDADE, ESTADO))
                ),
                () -> assertThrows(
                        // @NotEmpty - OBSERVACAO
                        Exception.class, () -> service.save(EnderecoUtil.factoryDto(UUID.fromString(USUARIO),"", COMPLEMENTO, CEP, CIDADE, ESTADO))
                ),
                () -> assertThrows(
                        // @NotEmpty - OBSERVACAO
                        Exception.class, () -> service.save(EnderecoUtil.factoryDto(UUID.fromString(USUARIO), LOGRADOURO, "", CEP, CIDADE, ESTADO))
                ),
                () -> assertThrows(
                        // @NotEmpty - OBSERVACAO
                        Exception.class, () -> service.save(EnderecoUtil.factoryDto(UUID.fromString(USUARIO), LOGRADOURO, COMPLEMENTO, CEP, "", ESTADO))
                ),
                () -> assertThrows(
                        // @NotEmpty - OBSERVACAO
                        Exception.class, () -> service.save(EnderecoUtil.factoryDto(UUID.fromString(USUARIO), LOGRADOURO, COMPLEMENTO, CEP, CIDADE, ""))
                ),
                () -> assertThrows(
                        // @UserVerify - OBSERVACAO
                        Exception.class, () -> service.save(EnderecoUtil.factoryDto(UUID.fromString(USUARIO), LOGRADOURO, COMPLEMENTO, CEP, CIDADE, ESTADO))
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
                () -> assertNotEquals(atual.getEstado(), modificado.getEstado()),
                () -> assertEquals(atual.getCep(), modificado.getCep()),

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
        var endereco = service.save(EnderecoUtil.factoryDto(UUID.fromString(USUARIO), LOGRADOURO, COMPLEMENTO, CEP, CIDADE, ESTADO));

        assertEquals(
                List.of(endereco), service.listAllByUsuario(UUID.fromString(USUARIO))
        );
    }

    private void saveUsuario() {
        usuario = usuarioService.save(UsuarioUtil.factoryDto()).getId();
    }
}
