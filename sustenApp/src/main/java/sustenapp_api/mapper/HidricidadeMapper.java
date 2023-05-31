package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.HidricidadeDto;
import sustenapp_api.model.HidricidadeModel;

public class HidricidadeMapper {
    public HidricidadeModel toMapper(HidricidadeDto objetoEntrada){
        HidricidadeModel objetoSaida = new HidricidadeModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        objetoSaida.setConsumo(0.0);
        return objetoSaida;
    }
}
