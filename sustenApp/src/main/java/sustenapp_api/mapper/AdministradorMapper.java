package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.FuncionarioDto;
import sustenapp_api.model.persist.AdministradorModel;

public class AdministradorMapper {
    public AdministradorModel toMapper(FuncionarioDto objetoEntrada){
        AdministradorModel objetoSaida = new AdministradorModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}
