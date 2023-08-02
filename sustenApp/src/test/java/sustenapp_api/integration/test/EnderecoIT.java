package sustenapp_api.integration.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import sustenapp_api.dto.POST.EnderecoDto;
import sustenapp_api.integration.util.EnderecoUtil;

import sustenapp_api.repository.EnderecoRepository;
import sustenapp_api.service.EnderecoService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static sustenapp_api.integration.util.ComodoUtil.USUARIO;
import static sustenapp_api.integration.util.EnderecoUtil.*;



@SpringBootTest
@AutoConfigureTestDatabase
public class EnderecoIT {

    @Autowired
    private EnderecoService service;

    @Autowired
    private EnderecoRepository repository;

    @BeforeEach
    private void setUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Testes de Cobertura e Validação do Metodo Save")
    public void save(){
        var atual = service.save(factoryDto());

        assertAll(
                () -> assertEquals(factoryDto().getUsuario(), atual.getUsuario()),
                () -> assertEquals(factoryDto().getCep(), atual.getCep()),
                () -> assertEquals(factoryDto().getCidade(), atual.getCidade()),
                () -> assertEquals(factoryDto().getEstado(), atual.getEstado()),
                () -> assertEquals(factoryDto().getLogradouro(), atual.getLogradouro()),
                () -> assertEquals(factoryDto().getComplemento(), atual.getComplemento()),

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
        var atual = service.save(factoryDto());

        assertAll(
                () -> assertDoesNotThrow(() -> service.delete(atual.getId())),
                () -> assertThrows(
                            Exception.class, () -> service.delete(UUID.fromString(ID))
                )
        );
    }

    @Test
    @DisplayName("Testes de Cobertura e Validação do Metodo FindById")
    public void findById() {
        var atual = service.save(factoryDto());

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
        var atual = service.save(factoryDto());
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
}
