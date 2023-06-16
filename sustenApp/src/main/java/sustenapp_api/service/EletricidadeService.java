package sustenapp_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.dependency.DateDependency;
import sustenapp_api.dto.EletricidadeDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.EletricidadeMapper;
import sustenapp_api.model.EletricidadeModel;
import sustenapp_api.repository.EletricidadeRepository;
import sustenapp_api.repository.TarifaRepository;
import sustenapp_api.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EletricidadeService {
    private final EletricidadeRepository eletricidadeRepository;
    private final UsuarioRepository usuarioRepository;
    private final TarifaRepository tarifaRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public EletricidadeModel save(EletricidadeDto eletricidade){
        verifyExistsUsuario(eletricidade.getUsuario());
        verifyExistsTarifa(eletricidade.getTarifa());

        return eletricidadeRepository.save(
                setTime(new EletricidadeMapper().toMapper(eletricidade))
        );
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID eletricidade){
        eletricidadeRepository.deleteById(eletricidade);
    }

    public EletricidadeModel update(EletricidadeModel eletricidade){
        verifyExistsEletricidade(eletricidade.getId());

        return eletricidadeRepository.save(eletricidade);
    }

    public EletricidadeModel findById(UUID eletricidade){
        return eletricidadeRepository.findById(eletricidade).map(this::getFull).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<EletricidadeModel> listAllByUsuario(UUID usuario) {
        return eletricidadeRepository.findAllByUsuario(usuario)
                .orElseThrow(() -> new ExceptionGeneric("", "", 404))
                .stream().map(this::getFull).toList();
    }

    private EletricidadeModel getFull(EletricidadeModel eletricidade){
        eletricidade.setValorEstimado(
                eletricidade.getConsumo() * tarifaRepository.findById(eletricidade.getTarifa()).get().getPreco()
        );

        eletricidade.setMediaConsumo(
                eletricidade.getConsumo() / 30
        );

        return eletricidade;
    }

    private EletricidadeModel setTime(EletricidadeModel eletricidade) {
        eletricidade.setData(DateDependency.getTime());
        return eletricidade;
    }

    private void verifyExistsUsuario(UUID usuario){
        if(!existsUsuario(usuario))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsEletricidade(UUID eletricidade){
        if(!existsEletricidade(eletricidade))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsTarifa(UUID tarifa){
        if(!existsTarifa(tarifa))
            throw new ExceptionGeneric("", "", 404);
    }

    private boolean existsUsuario(UUID usuario){
        return usuarioRepository.existsById(usuario);
    }

    private boolean existsEletricidade(UUID eletricidade){
        return eletricidadeRepository.existsById(eletricidade);
    }

    private boolean existsTarifa(UUID tarifa) {
        return tarifaRepository.existsById(tarifa);
    }
}