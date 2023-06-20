package sustenapp_api.mapper;

import sustenapp_api.dto.TarifaDto;
import sustenapp_api.model.persist.TarifaModel;
import sustenapp_api.model.type.RecursoTipo;

public class TarifaMapper {
    public TarifaModel toMapper(TarifaDto objetoEntrada){
        TarifaModel objetoSaida = new TarifaModel();
        objetoSaida.setPreco(objetoEntrada.getPreco());
        objetoSaida.setTipo(RecursoTipo.getRecurso(objetoEntrada.getTipo()));
        objetoSaida.setObservacao(objetoEntrada.getObservacao());
        return objetoSaida;
    }
}
