package sustenapp_api.integration.util;

import sustenapp_api.dto.POST.TarifaDto;
import sustenapp_api.model.persist.TarifaModel;
import sustenapp_api.model.type.RecursoTipo;

import java.time.LocalDateTime;
import java.util.UUID;

public class TarifaUtil {
    public static String ID = "", TIPO = "", OBSERVACAO = "";
    public static Double PRECO = 1.2;

    public static TarifaModel factory(LocalDateTime data) {
        return TarifaModel
                .builder()
                .id(UUID.fromString(ID))
                .preco(PRECO)
                .tipo(RecursoTipo.getRecurso(TIPO))
                .data(data)
                .observacao(OBSERVACAO)
                .build();
    }

    public static TarifaModel factory(Double preco, String tipo, LocalDateTime data, String observacao) {
        return TarifaModel
                .builder()
                .id(UUID.fromString(ID))
                .preco(preco)
                .tipo(RecursoTipo.getRecurso(tipo))
                .data(data)
                .observacao(observacao)
                .build();
    }

    public static TarifaDto factoryDto() {
        return TarifaDto
                .builder()
                .preco(PRECO)
                .tipo(TIPO)
                .observacao(OBSERVACAO)
                .build();
    }

    public static TarifaDto factoryDto(Double preco, String tipo, String observacao) {
        return TarifaDto
                .builder()
                .preco(preco)
                .tipo(tipo)
                .observacao(observacao)
                .build();
    }
}
