package sustenapp_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sustenapp_api.component.dependency.EmailDependency;
import sustenapp_api.component.treatment.EmailTreatment;
import sustenapp_api.component.validation.EmailExists;
import sustenapp_api.component.validation.EmailRecuperacaoExists;
import sustenapp_api.component.validation.RecuperacaoExists;
import sustenapp_api.dto.POST.EmailDto;
import sustenapp_api.dto.PUT.RecuperacaoDto;
import sustenapp_api.exception.BadRequestException;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.exception.NotFoundException;
import sustenapp_api.model.dynamic.RecuperacaoModel;
import sustenapp_api.repository.RecuperacaoRepository;
import sustenapp_api.repository.UsuarioRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RecuperacaoService {
    private final RecuperacaoRepository recuperacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RecuperacaoExists recuperacaoExists;
    private final EmailRecuperacaoExists emailRecuperacaoExists;
    private final EmailDependency emailDependency;
    private final EmailTreatment emailTreatment;

    public RecuperacaoModel check(String email) {
        RecuperacaoModel recuperacao = (emailRecuperacaoExists.isValid(email)) ? findCodigo(email) : save(email);

        sendEmail(recuperacao);
        return recuperacao;
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public RecuperacaoModel save(String email) {
        verifyExistsUsuario(email);

        return recuperacaoRepository.save(
                RecuperacaoModel.builder().email(email).codigo(getCodigo()).build()
        );
    }

    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 2000))
    private void sendEmail(RecuperacaoModel recuperacao) {
        emailDependency.sendEmail(
                EmailDto
                        .builder()
                        .destinatario(recuperacao.getEmail())
                        .assunto("SustenApp | Recuperacao de Credencias")
                        .texto(emailTreatment.emailRecuperacao(recuperacao.getCodigo()))
                        .build()
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
                () -> new NotFoundException("CODIGO DE RECUPERACAO")
        );
    }

    public void change(RecuperacaoDto recuperacao) {
        verifyExistsEmailAndCodigo(recuperacao.getEmail(), recuperacao.getCodigo());

        changeUsuario(recuperacao);
        delete(recuperacao.getEmail());
    }

    private void changeUsuario(RecuperacaoDto recuperacao) {
        var usuario = usuarioRepository.findByEmail(recuperacao.getEmail()).orElseThrow(
                () -> new NotFoundException("RECUPERACAO")
        );

        usuario.setSenha(recuperacao.getSenha());
        usuarioRepository.save(usuario);
    }

    private String getCodigo() {
        return Integer.toString(new Random().nextInt((999999 - 100000) + 1) + 100000);
    }

    private void verifyExistsEmailAndCodigo(String email, String codigo) {
        if (recuperacaoExists.isValid(email, codigo))
            throw new BadRequestException("RECUPERACAO");
    }

    private void verifyExistsUsuario(String email){
        if(!emailRecuperacaoExists.isValid(email))
            throw new NotFoundException("USUARIO");
    }
}