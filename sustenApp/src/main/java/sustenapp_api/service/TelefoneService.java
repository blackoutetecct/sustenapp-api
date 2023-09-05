package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.rule.Validation;
import sustenapp_api.component.validation.NotEmpty;
import sustenapp_api.component.validation.NotNull;
import sustenapp_api.component.validation.UsuarioValidation;
import sustenapp_api.dto.POST.ComodoDto;
import sustenapp_api.dto.POST.TelefoneDto;
import sustenapp_api.dto.PUT.TelefonePutDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.TelefoneMapper;
import sustenapp_api.model.persist.TelefoneModel;
import sustenapp_api.repository.TelefoneRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TelefoneService implements Validation<TelefoneDto>  {
    private final TelefoneRepository telefoneRepository;
    private final UsuarioValidation usuarioValidation;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public TelefoneModel save(@Valid TelefoneDto telefone) {
      validated(telefone);
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

    @Override
    public boolean validate(TelefoneDto value) {
        return Stream.of(
                NotNull.isValid(value.getNumero()),
                NotEmpty.isValid(value.getNumero()),
                NotNull.isValid(value.getUsuario()),
                usuarioValidation.isValid(value.getUsuario())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validated(TelefoneDto value) {
        if(!validate(value))
            throw new ExceptionGeneric("", "", 404);
    }
}
