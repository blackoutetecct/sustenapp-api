package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.POST.PreferenciaDto;
import sustenapp_api.dto.PUT.PreferenciaPutDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.PreferenciaMapper;
import sustenapp_api.model.persist.PreferenciaModel;
import sustenapp_api.repository.PreferenciaRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PreferenciaService {
    private final PreferenciaRepository preferenciaRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public PreferenciaModel save(@Valid PreferenciaDto preferencia){
        return preferenciaRepository.save(new PreferenciaMapper().toMapper(preferencia));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID preferencia){
        preferenciaRepository.deleteById(preferencia);
    }

    public PreferenciaModel update(@Valid PreferenciaPutDto preferencia){
        return preferenciaRepository.save(new PreferenciaMapper().toMapper(preferencia, findById(preferencia.getId())));
    }

    public PreferenciaModel findById(UUID preferencia){
        return preferenciaRepository.findById(preferencia).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<PreferenciaModel> listAll(){
        return preferenciaRepository.findAll();
    }
}