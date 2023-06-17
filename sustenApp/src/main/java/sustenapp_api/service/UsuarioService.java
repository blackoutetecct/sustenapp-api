package sustenapp_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.dependency.EmailDependency;
import sustenapp_api.dto.EmailDto;
import sustenapp_api.dto.UsuarioDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.UsuarioMapper;
import sustenapp_api.model.UsuarioModel;
import sustenapp_api.repository.EnderecoRepository;
import sustenapp_api.repository.TelefoneRepository;
import sustenapp_api.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public UsuarioModel save(UsuarioDto usuario){
        verifyExistsEmailAndCPF(usuario.getEmail(), usuario.getCpf());

        return usuarioRepository.save(new UsuarioMapper().toMapper(usuario));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID usuario){
        usuarioRepository.deleteById(usuario);
    }

    public UsuarioModel update(UsuarioModel usuario){
        verifyExistsUsuario(usuario.getId());

        return usuarioRepository.save(usuario);
    }

    public UsuarioModel findById(UUID usuario){
        return usuarioRepository.findById(usuario).map(this::getFull).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    // funcionamento em desenvolvimento
    public void forgotPassword(String email){
        verifyExistsEmail(email);

        EmailDependency.sendEmail(new EmailDto(email, "", ""));
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

    private void verifyExistsUsuario(UUID usuario){
        if(!existsUsuario(usuario))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsEmailAndCPF(String email, String cpf){
        if(existsEmailAndCPF(email, cpf))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsEmail(String email){
        if(!existsEmail(email))
            throw new ExceptionGeneric("", "", 404);
    }

    public boolean existsEmailAndSenha(String email, String senha){
        return usuarioRepository.existsByEmailAndSenha(email, senha);
    }

    private boolean existsUsuario(UUID usuario){
        return usuarioRepository.existsById(usuario);
    }

    private boolean existsEmailAndCPF(String email, String cpf){
        return usuarioRepository.existsByEmailAndCpf(email, cpf);
    }

    private boolean existsEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
}
