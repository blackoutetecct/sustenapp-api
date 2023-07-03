package sustenapp_api.mapper;

import sustenapp_api.dto.RecursoDto;
import sustenapp_api.model.persist.RecursoModel;
import sustenapp_api.model.type.RecursoTipo;

public class RecursoMapper {
    public RecursoModel toMapper(RecursoDto objetoEntrada){
        RecursoModel objetoSaida = new RecursoModel();
        objetoSaida.setUsuario(objetoEntrada.getUsuario());
        objetoSaida.setTipo(RecursoTipo.getRecurso(objetoEntrada.getTipo()));
        objetoSaida.setRenovavel(objetoEntrada.isRenovavel());
        objetoSaida.setConsumo(0.0);
        return objetoSaida;
    }
}
