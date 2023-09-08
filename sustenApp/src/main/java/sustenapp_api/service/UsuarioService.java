package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.rule.ValidationPost;
import sustenapp_api.component.rule.ValidationPut;
import sustenapp_api.component.validation.*;
import sustenapp_api.dto.POST.UsuarioDto;
import sustenapp_api.dto.PUT.UsuarioPutDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.UsuarioMapper;
import sustenapp_api.model.persist.UsuarioModel;
import sustenapp_api.repository.EnderecoRepository;
import sustenapp_api.repository.TelefoneRepository;
import sustenapp_api.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioService implements ValidationPost<UsuarioDto>, ValidationPut<UsuarioPutDto> {
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;
    private final UsuarioExists usuarioExists;
    private final EmailOrCPFExists emailOrCPFExists;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public UsuarioModel save(UsuarioDto usuario){
        validatedPost(usuario);
        verifyExistsEmailOrCPF(usuario.getEmail(), usuario.getCpf());
        return usuarioRepository.save(new UsuarioMapper().toMapper(usuario));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID usuario){
        usuarioRepository.deleteById(usuario);
    }

    public UsuarioModel update(UsuarioPutDto usuario){
        validatePut(usuario);
        return usuarioRepository.save(new UsuarioMapper().toMapper(usuario, findById(usuario.getId())));
    }

    public UsuarioModel findById(UUID usuario){
        return usuarioRepository.findById(usuario).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public UsuarioModel findByEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
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
        if(emailOrCPFExists.isValid(email, cpf))
            throw new ExceptionGeneric("", "", 404);
    }

    @Override
    public boolean validatePost(UsuarioDto value) {
        return Stream.of(
                NotNull.isValid(value.getNome()),
                NotEmpty.isValid(value.getNome()),
                NotNull.isValid(value.getSenha()),
                NotEmpty.isValid(value.getSenha()),
                NotNull.isValid(value.getTipo()),
                NotEmpty.isValid(value.getTipo()),
                NotNull.isValid(value.getEmail()),
                StringValid.isValid(value.getNome()),
                EmailValid.isValid(value.getEmail()),
                NotNull.isValid(value.getCpf()),
                CPFValid.isValid(value.getCpf())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validatedPost(UsuarioDto value) {
        if(!validatePost(value))
            throw new ExceptionGeneric("", "", 404);
    }

    @Override
    public boolean validatePut(UsuarioPutDto value) {
        return Stream.of(
                NotNull.isValid(value.getNome()),
                NotEmpty.isValid(value.getNome()),
                NotNull.isValid(value.getSenha()),
                NotEmpty.isValid(value.getSenha()),
                NotNull.isValid(value.getEmail()),
                StringValid.isValid(value.getNome()),
                EmailValid.isValid(value.getEmail()),
                NotNull.isValid(value.getId()),
                usuarioExists.isValid(value.getId())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validatedPut(UsuarioPutDto value) {
        if(!validatePut(value))
            throw new ExceptionGeneric("", "", 404);
    }
}
