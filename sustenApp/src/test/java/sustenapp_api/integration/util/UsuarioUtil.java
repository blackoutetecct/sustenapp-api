package sustenapp_api.integration.util;

import sustenapp_api.dto.UsuarioDto;
import sustenapp_api.model.persist.UsuarioModel;
import sustenapp_api.model.type.PerfilTipo;

import java.util.List;
import java.util.UUID;

public class UsuarioUtil {
    public static final String
            ID = "74d8f5a2-1d85-11ee-be56-0242ac120002", ID_FALSE = "1916a0ad-b90f-4446-84d3-a7c537b6b53d",
            NOME = "TESTE", CPF = "51248547829",
            EMAIL = "TESTE@teste.com", SENHA = "TESTE123", TIPO = "USUARIO";

    public static UsuarioModel factory() {
        return UsuarioModel.builder().id(UUID.fromString(ID)).nome(NOME).cpf(CPF).senha(SENHA).email(EMAIL).tipo(PerfilTipo.USUARIO).build();
    }

    public static UsuarioModel factory(String nome, String cpf, String senha, String email) {
        return UsuarioModel.builder().id(UUID.fromString(ID)).nome(nome).cpf(cpf).senha(senha).email(email).tipo(PerfilTipo.getRecurso(TIPO)).build();
    }

    public static UsuarioDto factoryDto() {
        return UsuarioDto.builder().nome(NOME).cpf(CPF).senha(SENHA).email(EMAIL).tipo(TIPO).build();
    }

    public static UsuarioDto factoryDto(String nome, String cpf, String senha, String email, String tipo) {
        return UsuarioDto.builder().nome(nome).cpf(cpf).senha(senha).email(email).tipo(tipo).build();
    }
}
