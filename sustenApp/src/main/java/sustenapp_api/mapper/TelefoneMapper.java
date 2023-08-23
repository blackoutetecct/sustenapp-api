package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.POST.TelefoneDto;
import sustenapp_api.dto.PUT.TelefonePutDto;
import sustenapp_api.model.persist.TelefoneModel;

public class TelefoneMapper {
    public TelefoneModel toMapper(TelefoneDto objetoEntrada){
        TelefoneModel objetoSaida = new TelefoneModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }

    public TelefoneModel toMapper(TelefonePutDto objetoEntrada, TelefoneModel objetoSaida){
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}
