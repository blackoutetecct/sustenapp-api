package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.PreferenciaDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.PreferenciaMapper;
import sustenapp_api.model.persist.PreferenciaModel;
import sustenapp_api.repository.PreferenciaRepository;
import sustenapp_api.repository.UsuarioRepository;

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

    public PreferenciaModel update(PreferenciaModel preferencia){
        verifyExistsPreferencia(preferencia.getId());

        return preferenciaRepository.save(preferencia);
    }

    public PreferenciaModel findById(UUID preferencia){
        return preferenciaRepository.findById(preferencia).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<PreferenciaModel> listAll(){
        return preferenciaRepository.findAll();
    }

    private void verifyExistsPreferencia(UUID preferencia){
        if(!existsPreferencia(preferencia))
            throw new ExceptionGeneric("", "", 404);
    }

    private boolean existsPreferencia(UUID preferencia){
        return preferenciaRepository.existsById(preferencia);
    }
}