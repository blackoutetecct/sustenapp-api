package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.UsuarioDto;
import sustenapp_api.model.UsuarioModel;

public class UsuarioMapper {
    public UsuarioModel toMapper(UsuarioDto objetoEntrada){
        UsuarioModel objetoSaida = new UsuarioModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}
