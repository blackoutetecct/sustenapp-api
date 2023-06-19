package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.RecursoDto;
import sustenapp_api.model.persist.RecursoModel;

public class RecursoMapper {
    public RecursoModel toMapper(RecursoDto objetoEntrada){
        RecursoModel objetoSaida = new RecursoModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        objetoSaida.setConsumo(0.0);
        return objetoSaida;
    }
}
