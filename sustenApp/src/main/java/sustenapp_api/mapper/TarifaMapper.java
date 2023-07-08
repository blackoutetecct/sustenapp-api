package sustenapp_api.mapper;

import sustenapp_api.dto.TarifaDto;
import sustenapp_api.model.persist.TarifaModel;
import sustenapp_api.model.type.RecursoTipo;

public class TarifaMapper {
    public TarifaModel toMapper(TarifaDto objetoEntrada){
        return TarifaModel
                .builder()
                .preco(objetoEntrada.getPreco())
                .observacao(objetoEntrada.getObservacao())
                .tipo(RecursoTipo.getRecurso(objetoEntrada.getTipo()))
                .build();
    }
}
