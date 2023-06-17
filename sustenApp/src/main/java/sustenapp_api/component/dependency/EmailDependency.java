package sustenapp_api.component.dependency;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import sustenapp_api.dto.EmailDto;
import sustenapp_api.mapper.EmailMapper;

@RequiredArgsConstructor
public class EmailDependency {
    private static JavaMailSender javaMailSender;

    public static EmailDto sendEmail(EmailDto emailDTO) {
        javaMailSender.send(new EmailMapper().toMapper(emailDTO, javaMailSender));
        return emailDTO;
    }
}
