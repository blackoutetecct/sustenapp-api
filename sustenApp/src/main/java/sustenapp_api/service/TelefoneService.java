package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.POST.TelefoneDto;
import sustenapp_api.dto.PUT.TelefonePutDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.TelefoneMapper;
import sustenapp_api.model.persist.TelefoneModel;
import sustenapp_api.repository.TelefoneRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TelefoneService {
    private final TelefoneRepository telefoneRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public TelefoneModel save(@Valid TelefoneDto telefone) {
        return telefoneRepository.save(new TelefoneMapper().toMapper(telefone));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID telefone) {
        telefoneRepository.deleteById(telefone);
    }

    public TelefoneModel update(@Valid TelefonePutDto telefone) {
        return telefoneRepository.save(new TelefoneMapper().toMapper(telefone, findById(telefone.getId())));
    }

    public TelefoneModel findById(UUID telefone) {
        return telefoneRepository.findById(telefone).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<TelefoneModel> listAllByUsuario(UUID usuario) {
        return telefoneRepository.findAllByUsuario(usuario).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }
}
