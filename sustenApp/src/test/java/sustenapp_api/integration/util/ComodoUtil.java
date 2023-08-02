package sustenapp_api.integration.util;

import sustenapp_api.dto.POST.ComodoDto;
import sustenapp_api.dto.PUT.ComodoPutDto;
import sustenapp_api.dto.PUT.EnderecoPutDto;
import sustenapp_api.model.persist.ComodoModel;

import java.util.UUID;

public class ComodoUtil {
    public static String ID = "74d8f5a2-1d85-11ee-be56-0242ac120002", NOME = "SALA", USUARIO = "84d8f5a2-1d85-11ee-be56-0242ac120002";

    public static ComodoModel factory(UUID usuario) {
        return ComodoModel.builder().id(UUID.fromString(ID)).usuario(usuario).nome(NOME).build();
    }

    public static ComodoModel factory(UUID usuario, String nome) {
        return ComodoModel.builder().id(UUID.fromString(ID)).usuario(usuario).nome(nome).build();
    }

    public static ComodoDto factoryDto(UUID usuario) {
        return ComodoDto.builder().usuario(usuario).nome(NOME).build();
    }

    public static ComodoPutDto factoryPutDto(UUID usuario) {
        return ComodoPutDto.builder().id(UUID.fromString(String.valueOf(usuario))).nome("Teste").build();
    }

    public static ComodoDto factoryDto(UUID usuario, String nome) {
        return ComodoDto.builder().usuario(usuario).nome(nome).build();
    }
}
