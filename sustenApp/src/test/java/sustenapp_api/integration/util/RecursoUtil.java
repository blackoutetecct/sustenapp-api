package sustenapp_api.integration.util;

import sustenapp_api.dto.POST.RecursoDto;
import sustenapp_api.dto.PUT.EnderecoPutDto;
import sustenapp_api.dto.PUT.RecursoPutDto;
import sustenapp_api.model.persist.RecursoModel;
import sustenapp_api.model.type.RecursoTipo;

import java.time.LocalDateTime;
import java.util.UUID;

public class RecursoUtil {
    public static String ID, TIPO = "HIDRICO";
    public static boolean RENOVAVEL = false;
    public static Double CONSUMO = 0.0;

    public static RecursoModel factory(UUID usuario, UUID tarifa, LocalDateTime data) {
        return RecursoModel
                .builder()
                .id(UUID.fromString(ID))
                .usuario(usuario)
                .tarifa(tarifa)
                .data(data)
                .tipo(RecursoTipo.getRecurso(TIPO))
                .renovavel(RENOVAVEL)
                .consumo(CONSUMO)
                .build();
    }

    public static RecursoModel factory(UUID usuario, UUID tarifa, LocalDateTime data, String tipo, Double consumo, boolean renovavel) {
        return RecursoModel
                .builder()
                .id(UUID.fromString(ID))
                .usuario(usuario)
                .tarifa(tarifa)
                .data(data)
                .tipo(RecursoTipo.getRecurso(tipo))
                .renovavel(renovavel)
                .consumo(consumo)
                .build();
    }

    public static RecursoDto factoryDto(UUID usuario, UUID tarifa) {
        return RecursoDto
                .builder()
                .usuario(usuario)
                .tarifa(tarifa)
                .tipo(TIPO)
                .renovavel(RENOVAVEL)
                .build();
    }

    public static RecursoPutDto factoryPutDto(UUID id) {
        return RecursoPutDto.builder().id(id).renovavel(RENOVAVEL).build();
    }

    public static RecursoDto factoryDto(UUID usuario, UUID tarifa, String tipo, boolean renovavel) {
        return RecursoDto
                .builder()
                .usuario(usuario)
                .tarifa(tarifa)
                .renovavel(renovavel)
                .build();
    }
}
