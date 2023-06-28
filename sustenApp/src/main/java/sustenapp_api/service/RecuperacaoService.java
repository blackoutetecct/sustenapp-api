package sustenapp_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sustenapp_api.dto.RecuperacaoDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.model.dynamic.RecuperacaoModel;
import sustenapp_api.repository.AdministradorRepository;
import sustenapp_api.repository.RecuperacaoRepository;
import sustenapp_api.repository.SuporteRepository;
import sustenapp_api.repository.UsuarioRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RecuperacaoService {
    private final RecuperacaoRepository recuperacaoRepository;
    private final AdministradorRepository administradorRepository;
    private final SuporteRepository suporteRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public RecuperacaoModel save(String email) {
        verifyExistsEmail(email);

        return recuperacaoRepository.save(
                RecuperacaoModel.builder().email(email).codigo(getCodigo()).build()
        );
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(String email) {
        recuperacaoRepository.deleteByEmail(email);
    }

    @Scheduled(cron = "* 59 23 * * *")
    public void dropTable() {
        recuperacaoRepository.deleteAll();
    }

    public RecuperacaoModel findCodigo(String email) {
        return recuperacaoRepository.findByEmail(email).orElseThrow(
                () -> new ExceptionGeneric("", "", 404)
        );
    }

    public void change(RecuperacaoDto recuperacao) {
        verifyExistsEmailAndCodigo(recuperacao.getEmail(), recuperacao.getCodigo());

        changeUsuario(recuperacao);
        changeSuporte(recuperacao);
        changeAdministrador(recuperacao);
    }

    private void changeUsuario(RecuperacaoDto recuperacao) {
        var usuario = usuarioRepository.findByEmail(recuperacao.getEmail()).orElse(null);

        if(usuario.equals(null))
            return;

        usuario.setSenha(recuperacao.getSenha());
        usuarioRepository.save(usuario);
    }

    private void changeAdministrador(RecuperacaoDto recuperacao) {
        var administrador = administradorRepository.findByEmail(recuperacao.getEmail()).orElse(null);

        if(administrador.equals(null))
            return;

        administrador.setSenha(recuperacao.getSenha());
        administradorRepository.save(administrador);
    }

    private void changeSuporte(RecuperacaoDto recuperacao) {
        var suporte = suporteRepository.findByEmail(recuperacao.getEmail()).orElse(null);

        if(suporte.equals(null))
            return;

        suporte.setSenha(recuperacao.getSenha());
        suporteRepository.save(suporte);
    }

    private String getCodigo() {
        return Integer.toString(new Random().nextInt((999999 - 100000) + 1) + 100000);
    }

    private void verifyExistsEmail(String email) {
        if (existsEmail(email))
            throw new ExceptionGeneric("", "", 404);
    }

    private void verifyExistsEmailAndCodigo(String email, String codigo) {
        if (existsEmailAndCodigo(email, codigo))
            throw new ExceptionGeneric("", "", 404);
    }

    private boolean existsEmail(String email) {
        return recuperacaoRepository.existsByEmail(email);
    }

    private boolean existsEmailAndCodigo(String email, String codigo) {
        return recuperacaoRepository.existsByEmailAndCodigo(email, codigo);
    }
}