package sustenapp_api.component.treatment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.component.dependency.FileDependency;

@Component
@RequiredArgsConstructor
public class EmailTreatment {
    private final FileDependency fileDependency;

    public String emailRecuperacao(String codigo) {
        return fileDependency.read("email.txt").replace("[CODIGO]", codigo);
    }
}
