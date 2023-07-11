package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.UsuarioDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.UsuarioMapper;
import sustenapp_api.model.persist.UsuarioModel;
import sustenapp_api.model.type.PerfilTipo;
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
    public UsuarioModel save(@Valid UsuarioDto usuario){
        verifyExistsEmailOrCPF(usuario.getEmail(), usuario.getCpf());
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

        return usuarioRepository.save(new UsuarioMapper().toMapper(usuario));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID usuario){
        usuarioRepository.deleteById(usuario);
    }

    public UsuarioModel update(UsuarioModel usuario){
        verifyExistsUsuario(usuario.getId());
        verifyExistsCPFAndTipo(usuario.getCpf(), usuario.getTipo());

        return usuarioRepository.save(usuario);
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

    private void verifyExistsUsuario(UUID usuario){
        if(!existsUsuario(usuario))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsEmailOrCPF(String email, String cpf){
        if(existsEmailOrCPF(email, cpf))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsCPFAndTipo(String cpf, PerfilTipo tipo){
        if(!existsCPFAndTipo(cpf, tipo))
            throw new ExceptionGeneric("", "", 404);
    }

    public boolean existsEmailAndSenhaAndTipo(String email, String senha, String tipo){
        return usuarioRepository.existsByEmailAndSenhaAndTipo(email, senha, PerfilTipo.getRecurso(tipo));
    }

    private boolean existsUsuario(UUID usuario){
        return usuarioRepository.existsById(usuario);
    }

    private boolean existsEmailOrCPF(String email, String cpf){
        return usuarioRepository.existsByEmailOrCpf(email, cpf);
    }

    private boolean existsCPFAndTipo(String cpf, PerfilTipo tipo){
        return usuarioRepository.existsByCpfAndTipo(cpf, tipo);
    }
}