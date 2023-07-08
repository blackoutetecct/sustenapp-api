package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.ComodoDto;
import sustenapp_api.model.persist.ComodoModel;

public class ComodoMapper {
    public ComodoModel toMapper(ComodoDto objetoEntrada){
        ComodoModel objetoSaida = new ComodoModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}
