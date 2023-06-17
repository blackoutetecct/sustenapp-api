package sustenapp_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.FuncionarioDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.AdministradorMapper;
import sustenapp_api.model.persist.AdministradorModel;
import sustenapp_api.repository.AdministradorRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdministradorService {
    private final AdministradorRepository administradorRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public AdministradorModel save(FuncionarioDto administrador){
        verifyExistsEmailAndCPF(administrador.getEmail(), administrador.getCpf());

        return administradorRepository.save(new AdministradorMapper().toMapper(administrador));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID administrador){
        administradorRepository.deleteById(administrador);
    }

    public AdministradorModel update(AdministradorModel administrador){
        verifyExistsAdministrador(administrador.getId());

        return administradorRepository.save(administrador);
    }

    public AdministradorModel findById(UUID administrador){
        return administradorRepository.findById(administrador).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<AdministradorModel> listAll(){
        return administradorRepository.findAll();
    }

    private void verifyExistsAdministrador(UUID administrador){
        if(!existsAdministrador(administrador))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsEmailAndCPF(String email, String cpf){
        if(existsEmailAndCPF(email, cpf))
            throw new ExceptionGeneric("", "", 404);
    }

    public boolean existsEmailAndSenha(String email, String senha){
        return administradorRepository.existsByEmailAndSenha(email, senha);
    }

    private boolean existsAdministrador(UUID administrador){
        return administradorRepository.existsById(administrador);
    }

    private boolean existsEmailAndCPF(String email, String cpf){
        return administradorRepository.existsByEmailAndCpf(email, cpf);
    }
}
