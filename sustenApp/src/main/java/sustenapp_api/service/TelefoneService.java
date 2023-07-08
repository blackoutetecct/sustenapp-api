package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.TelefoneDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.TelefoneMapper;
import sustenapp_api.model.persist.TelefoneModel;
import sustenapp_api.repository.TelefoneRepository;
import sustenapp_api.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TelefoneService {
    private final TelefoneRepository telefoneRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public TelefoneModel save(@Valid TelefoneDto telefone){
        return telefoneRepository.save(new TelefoneMapper().toMapper(telefone));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID telefone){
        telefoneRepository.deleteById(telefone);
    }

    public TelefoneModel update(TelefoneModel telefone){
       verifyExistsTelefone(telefone.getId());

       return telefoneRepository.save(telefone);
    }

    public TelefoneModel findById(UUID telefone){
        return telefoneRepository.findById(telefone).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<TelefoneModel> listAllByUsuario(UUID usuario){
        return telefoneRepository.findAllByUsuario(usuario).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    private void verifyExistsTelefone(UUID telefone){
        if(existsTelefone(telefone))
            throw new ExceptionGeneric("", "", 404);
    }

    private boolean existsTelefone(UUID telefone){
        return telefoneRepository.existsById(telefone);
    }
}
