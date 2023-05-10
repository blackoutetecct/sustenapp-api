package sustenapp_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.UsuarioDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.UsuarioMapper;
import sustenapp_api.model.UsuarioModel;
import sustenapp_api.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

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
        return usuarioRepository.findById(usuario).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<UsuarioModel> listAll(){
        return usuarioRepository.findAll();
    }

    private void verifyExistsUsuario(UUID usuario){
        if(!existsUsuario(usuario))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsEmailAndCPF(String email, String cpf){
        if(existsEmailAndCPF(email, cpf))
            throw new ExceptionGeneric("", "", 404);
    }

    private boolean existsUsuario(UUID usuario){
        return usuarioRepository.existsById(usuario);
    }

    private boolean existsEmailAndCPF(String email, String cpf){
        return usuarioRepository.existsByEmailAndCpf(email, cpf);
    }
}
