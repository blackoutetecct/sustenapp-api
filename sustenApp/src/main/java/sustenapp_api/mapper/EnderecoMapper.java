package sustenapp_api.mapper;

import org.springframework.beans.BeanUtils;
import sustenapp_api.dto.POST.EnderecoDto;
import sustenapp_api.dto.PUT.EnderecoPutDto;
import sustenapp_api.model.persist.EnderecoModel;

public class EnderecoMapper {
    public EnderecoModel toMapper(EnderecoDto objetoEntrada){
        EnderecoModel objetoSaida = new EnderecoModel();
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }

    public EnderecoModel toMapper(EnderecoPutDto objetoEntrada, EnderecoModel objetoSaida){
        BeanUtils.copyProperties(objetoEntrada, objetoSaida);
        return objetoSaida;
    }
}
