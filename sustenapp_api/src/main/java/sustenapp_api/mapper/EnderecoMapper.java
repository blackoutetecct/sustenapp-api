package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.EnderecoDto;
import sustenapp_api.dto.UsuarioDto;
import sustenapp_api.model.EnderecoModel;
import sustenapp_api.model.UsuarioModel;

public class EnderecoMapper {
    public EnderecoModel toMapper(EnderecoDto objetoEntrada){
        EnderecoModel objetoSaida = new EnderecoModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}
