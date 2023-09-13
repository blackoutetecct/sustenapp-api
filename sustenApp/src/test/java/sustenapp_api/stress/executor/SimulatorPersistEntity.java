package sustenapp_api.stress.executor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.repository.*;
import sustenapp_api.stress.util.FactoryEntity;

@Component
@RequiredArgsConstructor
public class SimulatorPersistEntity {
    private final ComodoRepository comodoRepository;
    private final DispositivoRepository dispositivoRepository;
    private final EnderecoRepository enderecoRepository;
    private final PreferenciaRepository preferenciaRepository;
    private final RecuperacaoRepository recuperacaoRepository;
    private final RecursoRepository recursoRepository;
    private final TarifaRepository tarifaRepository;
    private final TelefoneRepository telefoneRepository;
    private final UsuarioRepository usuarioRepository;

    public void runSimulationSaveTest() {
        var usuario = usuarioRepository.save(FactoryEntity.getUsuario());
        var telefone = telefoneRepository.save(FactoryEntity.getTelefone(usuario.getId()));
        var preferencia = preferenciaRepository.save(FactoryEntity.getPreferencia(usuario.getId()));
        var endereco = enderecoRepository.save(FactoryEntity.getEndereco(usuario.getId()));
        var comodo = comodoRepository.save(FactoryEntity.getComodo(usuario.getId()));
        var dispositivo = dispositivoRepository.save(FactoryEntity.getDispositivo(comodo.getId()));
        var recuperacao = recuperacaoRepository.save(FactoryEntity.getRecuperacao());
        var tarifa = tarifaRepository.save(FactoryEntity.getTarifa());
        var recurso = recursoRepository.save(FactoryEntity.getRecurso(usuario.getId(), tarifa.getId()));

        System.out.println(usuario);
        System.out.println(telefone);
        System.out.println(preferencia);
        System.out.println(endereco);
        System.out.println(dispositivo);
        System.out.println(recuperacao);
        System.out.println(tarifa);
        System.out.println(recurso);
    }
}