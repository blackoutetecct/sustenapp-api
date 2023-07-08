package sustenapp_api.mapper;

import sustenapp_api.dto.UsuarioDto;
import sustenapp_api.model.persist.UsuarioModel;
import sustenapp_api.model.type.PerfilTipo;

public class UsuarioMapper {
    public UsuarioModel toMapper(UsuarioDto objetoEntrada){
        return UsuarioModel
                .builder()
                .nome(objetoEntrada.getNome())
                .email(objetoEntrada.getEmail())
                .cpf(objetoEntrada.getCpf())
                .senha(objetoEntrada.getSenha())
                .tipo(PerfilTipo.getRecurso(objetoEntrada.getTipo()))
                .build();
    }
}
