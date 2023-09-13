package sustenapp_api.stress.util;

import sustenapp_api.model.dynamic.RecuperacaoModel;
import sustenapp_api.model.persist.*;
import sustenapp_api.model.type.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class FactoryEntity {
    public static ComodoModel getComodo(UUID usuario) {
        return ComodoModel
                .builder()
                .usuario(usuario)
                .nome("LAB 4")
                .build();
    }

    public static DispositivoModel getDispositivo(UUID comodo) {
        return DispositivoModel
                .builder()
                .comodo(comodo)
                .nome("TV")
                .porta(0)
                .build();
    }

    public static EnderecoModel getEndereco( UUID usuario) {
        return EnderecoModel
                .builder()
                .usuario(usuario)
                .logradouro("Escola Técnica Estadual da Cidade Tiradentes")
                .complemento("1º Andar")
                .cep("08485-310")
                .cidade("São Paulo")
                .estado("SP")
                .build();
    }

    public static RecursoModel getRecurso(UUID usuario, UUID tarifa) {
        return RecursoModel
                .builder()
                .usuario(usuario)
                .tarifa(tarifa)
                .data(LocalDateTime.now())
                .tipo(RecursoTipo.ELETRICO)
                .renovavel(true)
                .consumo(10.0)
                .build();
    }

    public static TarifaModel getTarifa() {
        return TarifaModel
                .builder()
                .preco(10.0)
                .tipo(RecursoTipo.ELETRICO)
                .data(LocalDateTime.now())
                .observacao("SEM OBSERVAÇÕES")
                .build();
    }

    public static TelefoneModel getTelefone(UUID usuario) {
        return TelefoneModel
                .builder()
                .usuario(usuario)
                .numero("1195802019")
                .build();
    }

    public static RecuperacaoModel getRecuperacao() {
        return RecuperacaoModel
                .builder()
                .email("teste@gmail.com")
                .codigo("123456")
                .build();
    }

    public static PreferenciaModel getPreferencia(UUID usuario) {
        return PreferenciaModel
                .builder()
                .usuario(usuario)
                .gerenciarEnergiaNaoRenovavel(true)
                .gerenciarAguaNaoRenovavel(true)
                .gerenciarEnergiaRenovavel(true)
                .gerenciarAguaRenovavel(true)
                .build();
    }

    public static UsuarioModel getUsuario() {
        return UsuarioModel
                .builder()
                .nome("TESTE")
                .cpf("51122458088")
                .senha("123456789")
                .email("teste@gmail.com")
                .tipo(PerfilTipo.USUARIO)
                .build();
    }
}
