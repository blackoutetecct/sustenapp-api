package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.EnderecoDto;
import sustenapp_api.model.persist.EnderecoModel;

public class EnderecoMapper {
    public EnderecoModel toMapper(EnderecoDto objetoEntrada){
        EnderecoModel objetoSaida = new EnderecoModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}
