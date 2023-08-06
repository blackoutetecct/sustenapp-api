package sustenapp_api.integration.util;

import sustenapp_api.dto.POST.DispositivoDto;
import sustenapp_api.dto.PUT.DispositivoPutDto;
import sustenapp_api.dto.PUT.EnderecoPutDto;
import sustenapp_api.model.persist.DispositivoModel;

import java.util.UUID;

public class DispositivoUtil {
    public static String ID, NOME = "LAMPADA";
    public static int PORTA = 4;

    public static DispositivoModel factory(UUID comodo) {
        return DispositivoModel
                .builder()
                .id(UUID.fromString(ID))
                .comodo(comodo)
                .nome(NOME)
                .porta(PORTA)
                .build();
    }

    public static DispositivoModel factory(UUID comodo, String nome, int porta) {
        return DispositivoModel
                .builder()
                .id(UUID.fromString(ID))
                .comodo(comodo)
                .nome(nome)
                .porta(porta)
                .build();
    }

    public static DispositivoDto factoryDto(UUID comodo) {
        return DispositivoDto
                .builder()
                .comodo(comodo)
                .nome(NOME)
                .porta(PORTA)
                .build();
    }

    public static DispositivoPutDto factoryPutDto(UUID id) {
        return DispositivoPutDto.builder().id(id).porta(PORTA).nome("TESTE").build();
    }

    public static DispositivoDto factoryDto(UUID comodo, String nome, int porta) {
        return DispositivoDto
                .builder()
                .comodo(comodo)
                .nome(nome)
                .porta(porta)
                .build();
    }
}
