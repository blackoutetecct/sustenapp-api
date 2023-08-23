package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sustenapp_api.dto.POST.UsuarioDto;
import sustenapp_api.dto.PUT.UsuarioPutDto;
import sustenapp_api.model.persist.UsuarioModel;
import sustenapp_api.model.type.PerfilTipo;

public class UsuarioMapper {
    public UsuarioModel toMapper(UsuarioDto objetoEntrada){
        return UsuarioModel
                .builder()
                .nome(objetoEntrada.getNome())
                .email(objetoEntrada.getEmail())
                .cpf(objetoEntrada.getCpf())
                .senha(encode(objetoEntrada.getSenha()))
                .tipo(PerfilTipo.getRecurso(objetoEntrada.getTipo()))
                .build();
    }

    public UsuarioModel toMapper(UsuarioPutDto objetoEntrada, UsuarioModel objetoSaida){
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        objetoSaida.setSenha(encode(objetoEntrada.getSenha()));
        return objetoSaida;
    }

    private String encode(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
