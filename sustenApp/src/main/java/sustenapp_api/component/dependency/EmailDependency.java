package sustenapp_api.component.dependency;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import sustenapp_api.dto.POST.EmailDto;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.mapper.EmailMapper;

@Component
@RequiredArgsConstructor
public class EmailDependency {
    private final JavaMailSender javaMailSender;

    public void sendEmail(EmailDto emailDTO) {
        try {
            javaMailSender.send(new EmailMapper().toMapper(emailDTO, javaMailSender));
        } catch (Exception ignored) {
            throw new ExceptionGeneric("", "", 500);
        }
    }
}