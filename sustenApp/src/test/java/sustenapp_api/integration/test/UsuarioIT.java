package sustenapp_api.integration.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import sustenapp_api.dto.POST.UsuarioDto;
import sustenapp_api.integration.util.UsuarioUtil;
import sustenapp_api.model.type.PerfilTipo;
import sustenapp_api.repository.UsuarioRepository;
import sustenapp_api.service.UsuarioService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static sustenapp_api.integration.util.UsuarioUtil.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class UsuarioIT {
    @Autowired private UsuarioService service;
    @Autowired private UsuarioRepository repository;

    @BeforeEach
    private void setUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Testes de Cobertura e Validacao do Metodo Save")
    public void save() {
        var atual = service.save(factoryDto());

        assertAll(
                () -> assertEquals(factoryDto().getNome(), atual.getNome()),
                () -> assertEquals(factoryDto().getCpf(), atual.getCpf()),
                () -> assertEquals(factoryDto().getEmail(), atual.getEmail()),
                () -> assertEquals(factoryDto().getTipo(), atual.getTipo().toString()),

                () -> assertThrows(
                        // @NotNull e @NotEmpty
                        Exception.class, () -> service.save(UsuarioDto.builder().build())
                ),
                () -> assertThrows(
                        // @StringVerify
                        Exception.class, () -> service.save(UsuarioUtil.factoryDto(NOME.concat("1"), CPF, SENHA, EMAIL, TIPO))
                ),
                () -> assertThrows(
                        // @Email
                        Exception.class, () -> service.save(UsuarioUtil.factoryDto(NOME, CPF, SENHA, "TESTE", TIPO))
                ),
                () -> assertThrows(
                        // @CPFVerify e @CPF -> suspeito
                        Exception.class, () -> service.save(UsuarioUtil.factoryDto(NOME, "123456789", SENHA, EMAIL, TIPO))
                ),
                () -> assertThrows(
                        // PerfilTipo.getRecurso()
                        Exception.class, () -> service.save(UsuarioUtil.factoryDto(NOME, CPF, SENHA, EMAIL, "TESTE"))
                ),
                () -> assertThrows(
                        // verifyExistsEmailOrCPF()
                        Exception.class, () -> service.save(factoryDto())
                )
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validacao do Metodo Delete")
    public void delete() {
        var atual = service.save(factoryDto());

        assertAll(
                () -> assertDoesNotThrow(() -> service.delete(atual.getId())),
                () -> assertThrows(
                        Exception.class, () -> service.delete(UUID.fromString(ID_FALSE))
                )
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validacao do Metodo Update")
    public void update() {
        var atual = service.save(factoryDto());
        var modificado = service.update(factoryPutDto(atual.getId()));

        assertAll(
                () -> assertNotEquals(atual, modificado),
                () -> assertNotEquals(atual.getNome(), modificado.getNome()),
                () -> assertEquals(atual.getCpf(), modificado.getCpf()),

                () -> assertThrows(
                        Exception.class, () -> {
                            service.update(factoryPutDto(UUID.fromString(ID_FALSE)));
                        }
                )
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validacao do Metodo FindById")
    public void findById() {
        var atual = service.save(factoryDto());

        // adicionar save de endereco e telefone

        assertAll(
                () -> assertEquals(atual.getId(), service.findById(atual.getId()).getId()),
                () -> assertThrows(
                        Exception.class, () -> service.findById(UUID.fromString(ID_FALSE))
                )
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validacao do Metodo ListAll")
    public void listAll() {
        var usuario_01 = service.save(factoryDto());
        var usuario_02 = service.save(UsuarioUtil.factoryDto(NOME, "90977833054", SENHA, "teste@email", TIPO));

        assertEquals(
                List.of(usuario_01, usuario_02), service.listAll()
        );
    }
}