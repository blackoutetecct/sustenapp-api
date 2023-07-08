package sustenapp_api.mapper;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import sustenapp_api.dto.EmailDto;
import sustenapp_api.exception.ExceptionGeneric;

public class EmailMapper {
    public MimeMessage toMapper(EmailDto objetoEntrada, JavaMailSender javaMailSender) {
        try {
            MimeMessage objetoSaida = javaMailSender.createMimeMessage();
            MimeMessageHelper objetoAuxiliar = new MimeMessageHelper(objetoSaida, true);

            objetoAuxiliar.setTo(objetoEntrada.getDestinatario());
            objetoAuxiliar.setSubject(objetoEntrada.getAssunto());
            objetoAuxiliar.setText(objetoEntrada.getTexto(), true); // -> content, <boolean_is_html?>

            return objetoSaida;
        } catch (Exception ignored) {
            throw new ExceptionGeneric("", "", 500);
        }
    }
}
