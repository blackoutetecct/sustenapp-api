package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.POST.PreferenciaDto;
import sustenapp_api.dto.PUT.PreferenciaPutDto;
import sustenapp_api.model.persist.PreferenciaModel;

public class PreferenciaMapper {
    public PreferenciaModel toMapper(PreferenciaDto objetoEntrada){
        PreferenciaModel objetoSaida = new PreferenciaModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }

    public PreferenciaModel toMapper(PreferenciaPutDto objetoEntrada, PreferenciaModel objetoSaida){
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}