package sustenapp_api.model.dynamic;

import java.time.LocalDateTime;

public record TokenValidModel(LocalDateTime time, String token, boolean valid) {

}
