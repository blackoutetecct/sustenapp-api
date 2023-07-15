package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.EnderecoDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.EnderecoMapper;
import sustenapp_api.model.persist.EnderecoModel;
import sustenapp_api.repository.EnderecoRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public EnderecoModel save(@Valid EnderecoDto endereco){
        return enderecoRepository.save(new EnderecoMapper().toMapper(endereco));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID endereco){
        enderecoRepository.deleteById(endereco);
    }

    public EnderecoModel update(EnderecoModel endereco){
        verifyExistsEndereco(endereco.getId());

        return enderecoRepository.save(endereco);
    }

    public EnderecoModel findById(UUID endereco){
        return enderecoRepository.findById(endereco).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<EnderecoModel> listAllByUsuario(UUID usuario){
        return enderecoRepository.findAllByUsuario(usuario).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    private void verifyExistsEndereco(UUID endereco){
        if(existsEndereco(endereco))
            throw new ExceptionGeneric("", "", 404);
    }

    private boolean existsEndereco(UUID endereco){
        return enderecoRepository.existsById(endereco);
    }
}
