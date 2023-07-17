package sustenapp_api.integration.util;

import sustenapp_api.dto.TelefoneDto;
import sustenapp_api.model.persist.TelefoneModel;

import java.util.UUID;

public class TelefoneUtil {
    public static String ID = "74d8f5a2-1d85-11ee-be56-0242ac120002", NUMERO = "11910337911";

    public static TelefoneModel factory(UUID usuario) {
        return TelefoneModel.builder().id(UUID.fromString(ID)).usuario(usuario).numero(NUMERO).build();
    }

    public static TelefoneModel factory(UUID usuario, String numero) {
        return TelefoneModel.builder().id(UUID.fromString(ID)).usuario(usuario).numero(numero).build();
    }

    public static TelefoneDto factoryDto(UUID usuario) {
        return TelefoneDto.builder().usuario(usuario).numero(NUMERO).build();
    }

    public static TelefoneDto factoryDto(UUID usuario, String numero) {
        return TelefoneDto.builder().usuario(usuario).numero(numero).build();
    }
}
