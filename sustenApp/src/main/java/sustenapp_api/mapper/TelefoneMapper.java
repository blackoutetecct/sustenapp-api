package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.TelefoneDto;
import sustenapp_api.model.persist.TelefoneModel;

public class TelefoneMapper {
    public TelefoneModel toMapper(TelefoneDto objetoEntrada){
        TelefoneModel objetoSaida = new TelefoneModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}
