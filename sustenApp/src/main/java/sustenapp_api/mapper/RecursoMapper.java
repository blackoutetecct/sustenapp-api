package sustenapp_api.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.component.dependency.DateDependency;
import sustenapp_api.dto.RecursoDto;
import sustenapp_api.model.persist.RecursoModel;
import sustenapp_api.model.type.RecursoTipo;
import sustenapp_api.service.TarifaService;

@Component
@RequiredArgsConstructor
public class RecursoMapper {
    private final TarifaService tarifaService;

    public RecursoModel toMapper(RecursoDto objetoEntrada){
        return RecursoModel
                .builder()
                .usuario(objetoEntrada.getUsuario())
                .renovavel(objetoEntrada.isRenovavel())
                .consumo(0.0)
                .tipo(RecursoTipo.getRecurso(objetoEntrada.getTipo()))
                .data(DateDependency.getDate())
                .tarifa(tarifaService.findLast().getId())
                .build();
    }
}
