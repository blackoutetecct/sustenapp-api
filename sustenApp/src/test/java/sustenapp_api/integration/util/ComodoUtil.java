package sustenapp_api.integration.util;

import sustenapp_api.dto.ComodoDto;
import sustenapp_api.model.persist.ComodoModel;

import java.util.UUID;

public class ComodoUtil {
    public static String ID = "", NOME = "SALA";

    public static ComodoModel factory(UUID usuario) {
        return ComodoModel.builder().id(UUID.fromString(ID)).usuario(usuario).nome(NOME).build();
    }

    public static ComodoModel factory(UUID usuario, String nome) {
        return ComodoModel.builder().id(UUID.fromString(ID)).usuario(usuario).nome(nome).build();
    }

    public static ComodoDto factoryDto(UUID usuario) {
        return ComodoDto.builder().usuario(usuario).nome(NOME).build();
    }

    public static ComodoDto factoryDto(UUID usuario, String nome) {
        return ComodoDto.builder().usuario(usuario).nome(nome).build();
    }
}
