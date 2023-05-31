package sustenapp_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.dependency.DateDependency;
import sustenapp_api.dto.HidricidadeDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.HidricidadeMapper;
import sustenapp_api.model.HidricidadeModel;
import sustenapp_api.repository.HidricidadeRepository;
import sustenapp_api.repository.TarifaRepository;
import sustenapp_api.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HidricidadeService {
    private final HidricidadeRepository hidricidadeRepository;
    private final UsuarioRepository usuarioRepository;
    private final TarifaRepository tarifaRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public HidricidadeModel save(HidricidadeDto hidricidade){
        verifyExistsUsuario(hidricidade.getUsuario());
        verifyExistsTarifa(hidricidade.getTarifa());

        return hidricidadeRepository.save(
                setTime(new HidricidadeMapper().toMapper(hidricidade))
        );
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID hidricidade){
        hidricidadeRepository.deleteById(hidricidade);
    }

    public HidricidadeModel update(HidricidadeModel hidricidade){
        verifyExistsHidricidade(hidricidade.getId());

        return hidricidadeRepository.save(hidricidade);
    }

    public HidricidadeModel findById(UUID hidricidade){
        return hidricidadeRepository.findById(hidricidade).map(this::getFull).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<HidricidadeModel> listAllByUsuario(UUID usuario){
        return hidricidadeRepository.findAllByUsuario(usuario)
                .orElseThrow(() -> new ExceptionGeneric("", "", 404))
                .stream().map(this::getFull).toList();
    }

    private HidricidadeModel getFull(HidricidadeModel hidricidade){
        hidricidade.setValorEstimado(
                hidricidade.getConsumo() * tarifaRepository.findById(hidricidade.getTarifa()).get().getPreco()
        );

        hidricidade.setMediaConsumo(
                hidricidade.getConsumo() / 30
        );

        return hidricidade;
    }

    private HidricidadeModel setTime(HidricidadeModel hidricidade) {
        hidricidade.setData(DateDependency.getTime());
        return hidricidade;
    }

    private void verifyExistsUsuario(UUID usuario){
        if(!existsUsuario(usuario))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsHidricidade(UUID hidricidade){
        if(!existsHidricidade(hidricidade))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsTarifa(UUID tarifa){
        if(!existsTarifa(tarifa))
            throw new ExceptionGeneric("", "", 404);
    }

    private boolean existsUsuario(UUID usuario){
        return usuarioRepository.existsById(usuario);
    }

    private boolean existsHidricidade(UUID hidricidade){
        return hidricidadeRepository.existsById(hidricidade);
    }

    private boolean existsTarifa(UUID tarifa) {
        return tarifaRepository.existsById(tarifa);
    }
}
