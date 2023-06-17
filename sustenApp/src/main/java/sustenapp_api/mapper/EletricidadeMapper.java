package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.EletricidadeDto;
import sustenapp_api.model.persist.EletricidadeModel;

public class EletricidadeMapper {
    public EletricidadeModel toMapper(EletricidadeDto objetoEntrada){
        EletricidadeModel objetoSaida = new EletricidadeModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        objetoSaida.setConsumo(0.0);
        return objetoSaida;
    }
}
