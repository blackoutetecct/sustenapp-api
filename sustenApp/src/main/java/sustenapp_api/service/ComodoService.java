package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.ComodoDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.ComodoMapper;
import sustenapp_api.model.persist.ComodoModel;
import sustenapp_api.repository.ComodoRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComodoService {
    private final ComodoRepository comodoRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public ComodoModel save(@Valid ComodoDto comodo){
        return comodoRepository.save(new ComodoMapper().toMapper(comodo));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID comodo){
        comodoRepository.deleteById(comodo);
    }

    public ComodoModel update(ComodoModel comodo){
       verifyExistsComodo(comodo.getId());

       return comodoRepository.save(comodo);
    }

    public ComodoModel findById(UUID comodo){
        return comodoRepository.findById(comodo).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<ComodoModel> listAllByUsuario(UUID usuario){
        return comodoRepository.findAllByUsuario(usuario).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    private void verifyExistsComodo(UUID comodo){
        if(existsComodo(comodo))
            throw new ExceptionGeneric("", "", 404);
    }

    private boolean existsComodo(UUID comodo){
        return comodoRepository.existsById(comodo);
    }
}
