package sustenapp_api.integration.util;

import sustenapp_api.dto.EnderecoDto;
import sustenapp_api.model.persist.EnderecoModel;

import java.util.UUID;

public class EnderecoUtil {
    public static String
        ID = "", USUARIO = "",
        LOGRADOURO = "", COMPLEMENTO = "", CEP = "",
        CIDADE = "", ESTADO = "";

    public static EnderecoModel factory() {
        return EnderecoModel
                .builder()
                .id(UUID.fromString(ID))
                .usuario(UUID.fromString(USUARIO))
                .logradouro(LOGRADOURO)
                .complemento(COMPLEMENTO)
                .cep(CEP)
                .cidade(CIDADE)
                .estado(ESTADO)
                .build();
    }

    public static EnderecoModel factory(UUID usuario, String logradouro, String complemento, String cep, String cidade, String estado) {
        return EnderecoModel
                .builder()
                .id(UUID.fromString(ID))
                .usuario(usuario)
                .logradouro(logradouro)
                .complemento(complemento)
                .cep(cep)
                .cidade(cidade)
                .estado(estado)
                .build();
    }

    public static EnderecoDto factoryDto() {
        return EnderecoDto
                .builder()
                .usuario(UUID.fromString(USUARIO))
                .logradouro(LOGRADOURO)
                .complemento(COMPLEMENTO)
                .cep(CEP)
                .cidade(CIDADE)
                .estado(ESTADO)
                .build();
    }

    public static EnderecoDto factoryDto(UUID usuario, String logradouro, String complemento, String cep, String cidade, String estado) {
        return EnderecoDto
                .builder()
                .usuario(usuario)
                .logradouro(logradouro)
                .complemento(complemento)
                .cep(cep)
                .cidade(cidade)
                .estado(estado)
                .build();
    }
}
