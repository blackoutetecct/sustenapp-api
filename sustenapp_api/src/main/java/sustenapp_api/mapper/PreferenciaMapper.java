package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.PreferenciaDto;
import sustenapp_api.model.PreferenciaModel;

public class PreferenciaMapper {
    public PreferenciaModel toMapper(PreferenciaDto objetoEntrada){
        PreferenciaModel objetoSaida = new PreferenciaModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}
