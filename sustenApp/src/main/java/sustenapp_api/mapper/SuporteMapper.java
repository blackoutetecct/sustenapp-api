package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.FuncionarioDto;
import sustenapp_api.model.SuporteModel;

public class SuporteMapper {
    public SuporteModel toMapper(FuncionarioDto objetoEntrada){
        SuporteModel objetoSaida = new SuporteModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}
