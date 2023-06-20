package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.DispositivoDto;
import sustenapp_api.model.persist.DispositivoModel;

public class DispositivoMapper {
    public DispositivoModel toMapper(DispositivoDto objetoEntrada){
        DispositivoModel objetoSaida = new DispositivoModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}
