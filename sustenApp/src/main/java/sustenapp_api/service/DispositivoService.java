package sustenapp_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.dependency.DateDependency;
import sustenapp_api.dto.DispositivoDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.DispositivoMapper;
import sustenapp_api.model.persist.DispositivoModel;
import sustenapp_api.repository.DispositivoRepository;
import sustenapp_api.repository.TarifaRepository;
import sustenapp_api.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DispositivoService {
    private final DispositivoRepository dispositivoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TarifaRepository tarifaRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public DispositivoModel save(DispositivoDto dispositivo){
        verifyExistsUsuario(dispositivo.getUsuario());
        verifyExistsTarifa(dispositivo.getTarifa());

        return dispositivoRepository.save(
                setTime(new DispositivoMapper().toMapper(dispositivo))
        );
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID dispositivo){
        dispositivoRepository.deleteById(dispositivo);
    }

    public DispositivoModel update(DispositivoModel dispositivo){
        verifyExistsDispositivo(dispositivo.getId());

        return dispositivoRepository.save(dispositivo);
    }

    public DispositivoModel findById(UUID dispositivo){
        return dispositivoRepository.findById(dispositivo).map(this::getFull).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public List<DispositivoModel> listAllByUsuario(UUID usuario) {
        return dispositivoRepository.findAllByUsuario(usuario)
                .orElseThrow(() -> new ExceptionGeneric("", "", 404))
                .stream().map(this::getFull).toList();
    }

    private DispositivoModel getFull(DispositivoModel dispositivo){
        dispositivo.setValorEstimado(
                dispositivo.getConsumo() * tarifaRepository.findById(dispositivo.getTarifa()).get().getPreco()
        );

        dispositivo.setMediaConsumo(
                dispositivo.getConsumo() / 30
        );

        return dispositivo;
    }

    private DispositivoModel setTime(DispositivoModel dispositivo) {
        dispositivo.setData(DateDependency.getDate());
        return dispositivo;
    }

    private void verifyExistsUsuario(UUID usuario){
        if(!existsUsuario(usuario))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsDispositivo(UUID dispositivo){
        if(!existsDispositivo(dispositivo))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsTarifa(UUID tarifa){
        if(!existsTarifa(tarifa))
            throw new ExceptionGeneric("", "", 404);
    }

    private boolean existsUsuario(UUID usuario){
        return usuarioRepository.existsById(usuario);
    }

    private boolean existsDispositivo(UUID dispositivo){
        return dispositivoRepository.existsById(dispositivo);
    }

    private boolean existsTarifa(UUID tarifa) {
        return tarifaRepository.existsById(tarifa);
    }
}