package sustenapp_api.mapper;

import sustenapp_api.dto.RecursoDto;
import sustenapp_api.model.persist.RecursoModel;
import sustenapp_api.model.type.RecursoTipo;

public class RecursoMapper {
    public RecursoModel toMapper(RecursoDto objetoEntrada){
        return RecursoModel
                .builder()
                .usuario(objetoEntrada.getUsuario())
                .renovavel(objetoEntrada.isRenovavel())
                .consumo(0.0)
                .tipo(RecursoTipo.getRecurso(objetoEntrada.getTipo()))
                .build();
    }
}
