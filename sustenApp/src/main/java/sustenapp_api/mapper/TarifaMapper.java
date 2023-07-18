package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.component.dependency.DateDependency;
import sustenapp_api.dto.POST.TarifaDto;
import sustenapp_api.dto.PUT.TarifaPutDto;
import sustenapp_api.model.persist.TarifaModel;
import sustenapp_api.model.type.RecursoTipo;

public class TarifaMapper {
    public TarifaModel toMapper(TarifaDto objetoEntrada){
        return TarifaModel
                .builder()
                .preco(objetoEntrada.getPreco())
                .observacao(objetoEntrada.getObservacao())
                .tipo(RecursoTipo.getRecurso(objetoEntrada.getTipo()))
                .data(DateDependency.getDate())
                .build();
    }

    public TarifaModel toMapper(TarifaPutDto objetoEntrada, TarifaModel objetoSaida){
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}
