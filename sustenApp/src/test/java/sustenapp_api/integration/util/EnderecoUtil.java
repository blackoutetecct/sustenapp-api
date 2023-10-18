package sustenapp_api.integration.util;

import sustenapp_api.dto.POST.EnderecoDto;
import sustenapp_api.dto.PUT.EnderecoPutDto;
import sustenapp_api.model.persist.EnderecoModel;

import java.util.UUID;

public class EnderecoUtil {
    public static String
        ID = "74d8f5a2-1d85-11ee-be56-0242ac120002", USUARIO = "84d8f5a2-1d85-11ee-be56-0242ac120002",
        LOGRADOURO = "Rua Igarape Agua Azul", COMPLEMENTO = "", CEP = "08485-310",
        CIDADE = "Sao Paulo", ESTADO = "SP";

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

    public static EnderecoDto factoryDto(UUID usuario) {
        return EnderecoDto
                .builder()
                .usuario(usuario)
                .logradouro(LOGRADOURO)
                .complemento(COMPLEMENTO)
                .cep(CEP)
                .cidade(CIDADE)
                .estado(ESTADO)
                .build();
    }

    public static EnderecoPutDto factoryPutDto(UUID id) {
        return EnderecoPutDto.builder().id(id).estado("Teste").logradouro(LOGRADOURO).complemento(COMPLEMENTO).cep(CEP).cidade(CIDADE).build();
    }

    public static EnderecoPutDto factoryPutDto(String estado, String logradouro, String complemento, String cep, String cidade) {
        return EnderecoPutDto.builder().id(UUID.fromString(ID)).estado(estado).logradouro(logradouro).complemento(complemento).cep(cep).cidade(cidade).build();
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
