package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.POST.ComodoDto;
import sustenapp_api.dto.PUT.ComodoPutDto;
import sustenapp_api.model.persist.ComodoModel;

public class ComodoMapper {
    public static ComodoModel toMapper(ComodoDto objetoEntrada){
        ComodoModel objetoSaida = new ComodoModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }

    public static ComodoModel toMapper(ComodoPutDto objetoEntrada, ComodoModel objetoSaida){
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}
