package sustenapp_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.FuncionarioDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.SuporteMapper;
import sustenapp_api.model.SuporteModel;
import sustenapp_api.repository.SuporteRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SuporteService {
    private final SuporteRepository suporteRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public SuporteModel save(FuncionarioDto suporte){
        verifyExistsEmailAndCPF(suporte.getEmail(), suporte.getCpf());

        return suporteRepository.save(new SuporteMapper().toMapper(suporte));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID suporte){
        suporteRepository.deleteById(suporte);
    }

    public SuporteModel update(SuporteModel suporte){
        verifyExistsSuporte(suporte.getId());

        return suporteRepository.save(suporte);
    }

    public SuporteModel findById(UUID suporte){
        return suporteRepository.findById(suporte).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<SuporteModel> listAll(){
        return suporteRepository.findAll();
    }

    private void verifyExistsSuporte(UUID suporte){
        if(!existsSuporte(suporte))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsEmailAndCPF(String email, String cpf){
        if(existsEmailAndCPF(email, cpf))
            throw new ExceptionGeneric("", "", 404);
    }

    public boolean existsEmailAndSenha(String email, String senha){
        return suporteRepository.existsByEmailAndSenha(email, senha);
    }

    private boolean existsSuporte(UUID suporte){
        return suporteRepository.existsById(suporte);
    }

    private boolean existsEmailAndCPF(String email, String cpf){
        return suporteRepository.existsByEmailAndCpf(email, cpf);
    }
}
