package sustenapp_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.dependency.DateDependency;
import sustenapp_api.dto.RecursoDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.RecursoMapper;
import sustenapp_api.model.persist.RecursoModel;
import sustenapp_api.repository.RecursoRepository;
import sustenapp_api.repository.TarifaRepository;
import sustenapp_api.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecursoService {
    private final RecursoRepository recursoRepository;
    private final TarifaRepository tarifaRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public RecursoModel save(@Valid RecursoDto recurso){
        return recursoRepository.save(
                setTime(new RecursoMapper().toMapper(recurso))
        );
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID recurso){
        recursoRepository.deleteById(recurso);
    }

    public RecursoModel update(RecursoModel recurso){
        verifyExistsRecurso(recurso.getId());

        return recursoRepository.save(recurso);
    }

    public RecursoModel findById(UUID recurso){
        return recursoRepository.findById(recurso).map(this::getFull).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<RecursoModel> listAllByUsuario(UUID usuario) {
        return recursoRepository.findAllByUsuario(usuario)
                .orElseThrow(() -> new ExceptionGeneric("", "", 404))
                .stream().map(this::getFull).toList();
    }

    private RecursoModel getFull(RecursoModel recurso){
        recurso.setValorEstimado(
                recurso.getConsumo() * tarifaRepository.findById(recurso.getTarifa()).get().getPreco()
        );

        recurso.setMediaConsumo(
                recurso.getConsumo() / 30
        );

        return recurso;
    }

    private RecursoModel setTime(RecursoModel recurso) {
        recurso.setData(DateDependency.getDate());
        return recurso;
    }

    private void verifyExistsRecurso(UUID recurso){
        if(!existsRecurso(recurso))
            throw new ExceptionGeneric("", "", 404);
    }

    private boolean existsRecurso(UUID recurso){
        return recursoRepository.existsById(recurso);
    }
}