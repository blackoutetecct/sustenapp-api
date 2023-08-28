package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.rule.Validation;
import sustenapp_api.component.validation.CPFValidation;
import sustenapp_api.component.validation.EmailValidation;
import sustenapp_api.component.validation.NotEmpty;
import sustenapp_api.component.validation.NotNull;
import sustenapp_api.dto.POST.ComodoDto;
import sustenapp_api.dto.POST.UsuarioDto;
import sustenapp_api.dto.PUT.UsuarioPutDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.UsuarioMapper;
import sustenapp_api.model.persist.UsuarioModel;
import sustenapp_api.model.type.PerfilTipo;
import sustenapp_api.repository.EnderecoRepository;
import sustenapp_api.repository.TelefoneRepository;
import sustenapp_api.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioService implements Validation<UsuarioDto> {
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public UsuarioModel save(@Valid UsuarioDto usuario){
        validated(usuario);
        verifyExistsEmailOrCPF(usuario.getEmail(), usuario.getCpf());
        return usuarioRepository.save(new UsuarioMapper().toMapper(usuario));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID usuario){
        usuarioRepository.deleteById(usuario);
    }

    public UsuarioModel update(@Valid UsuarioPutDto usuario){
        return usuarioRepository.save(new UsuarioMapper().toMapper(usuario, findById(usuario.getId())));
    }

    public UsuarioModel findById(UUID usuario){
        return usuarioRepository.findById(usuario).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );

        /*
        return usuarioRepository.findById(usuario).map(this::getFull).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
        */
    }

    public List<UsuarioModel> listAll(){
        return usuarioRepository.findAll();
    }

    public List<UsuarioModel> listAllFull(){
        return usuarioRepository.findAll().stream().map(this::getFull).toList();
    }

    private UsuarioModel getFull(UsuarioModel usuario) {
        usuario.setEnderecoList(
                enderecoRepository.findAllByUsuario(usuario.getId()).orElseThrow(
                        () -> new ExceptionGeneric("", "", 404)
                )
        );

        usuario.setTelefoneList(
                telefoneRepository.findAllByUsuario(usuario.getId()).orElseThrow(
                        () -> new ExceptionGeneric("", "", 404)
                )
        );

        return usuario;
    }

    private void verifyExistsEmailOrCPF(String email, String cpf){
        if(existsEmailOrCPF(email, cpf))
            throw new ExceptionGeneric("", "", 404);
    }

    private boolean existsEmailOrCPF(String email, String cpf){
        return usuarioRepository.existsByEmailOrCpf(email, cpf);
    }

    @Override
    public boolean validate(UsuarioDto value) {
        return Stream.of(
                NotNull.isValid(value.getNome()),
                NotEmpty.isValid(value.getNome()),
                NotNull.isValid(value.getSenha()),
                NotEmpty.isValid(value.getSenha()),
                NotNull.isValid(value.getTipo()),
                NotEmpty.isValid(value.getTipo()),
                NotNull.isValid(value.getEmail()),
                EmailValidation.isValid(value.getEmail()),
                NotNull.isValid(value.getCpf()),
                CPFValidation.isValid(value.getCpf())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validated(UsuarioDto value) {
        if(!validate(value))
            new ExceptionGeneric("", "", 404);
    }
}