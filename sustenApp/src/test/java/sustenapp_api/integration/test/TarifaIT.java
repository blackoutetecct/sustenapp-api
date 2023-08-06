package sustenapp_api.integration.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import sustenapp_api.dto.POST.TarifaDto;
import sustenapp_api.integration.util.TarifaUtil;
import sustenapp_api.repository.TarifaRepository;
import sustenapp_api.service.TarifaService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static sustenapp_api.integration.util.TarifaUtil.*;


@SpringBootTest
@AutoConfigureTestDatabase
public class TarifaIT {

    @Autowired private TarifaService service;
    @Autowired private TarifaRepository repository;

    @BeforeEach
    private void setUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Testes de Cobertura e Validação do Metodo save - Não ta salvando")
    public void save(){
        var atual = service.save(factoryDto());

        assertAll(
                () -> assertEquals(factoryDto().getObservacao(), atual.getObservacao()),
                () -> assertEquals(factoryDto().getPreco(), atual.getPreco()),
                () -> assertEquals(factoryDto().getTipo(), atual.getTipo().toString()),

                () -> assertThrows(
                        // @NotNull e @NotEmpty - OBSERVACAO
                        Exception.class, () -> service.save(TarifaDto.builder().build())
                ),

                () -> assertThrows(
                       // @Size(max = 8)
                        Exception.class, () -> service.save(TarifaUtil.factoryDto(PRECO, "TESTE1243", OBSERVACAO))
                ),

                () -> assertThrows(
                       // @Size(min = 7)
                        Exception.class, () -> service.save(TarifaUtil.factoryDto(PRECO, "TESTE1", OBSERVACAO))
                ),

                () -> assertThrows(
                        // @Positive - OBSERVACAO
                        Exception.class, () -> service.save(TarifaUtil.factoryDto(-1.0, TIPO, OBSERVACAO))
                ),

                () -> assertThrows(
                        // @NotEmpty - OBSERVACAO
                        Exception.class, () -> service.save(TarifaUtil.factoryDto(PRECO, TIPO, ""))
                )
        );

    }

    @Test
    @DisplayName("Testes de Cobertura e Validação do Metodo Delete")
    public void delete() {
        assertDoesNotThrow(() -> service.delete(service.save(factoryDto()).getId()));
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
    @DisplayName("Testes de Cobertura e Validação do Metodo listAll")
    public void listAll() {
        var esperado = List.of(
                service.save(factoryDto()),
                service.save(TarifaUtil.factoryDto(PRECO, TIPO, OBSERVACAO))
        );

        var data = LocalDateTime.now();

        assertEquals(
                service.listAll().stream()
                        .map(item -> {
                            item.setData(data);
                            return item;
                        })
                        .toList(),
                esperado.stream()
                        .map(item -> {
                            item.setData(data);
                            return item;
                        })
                        .toList()
        );
    }
}



