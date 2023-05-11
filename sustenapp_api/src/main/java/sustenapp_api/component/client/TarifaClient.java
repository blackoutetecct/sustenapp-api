package sustenapp_api.component.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TarifaClient {
    @Value("${api.tarifa.token}") private String token;

    public void getTarifa() {
        ResponseEntity<Object> objeto = new RestTemplate()
                .exchange(
                        "https://apise.way2.com.br/v1/agentes?apiKey="+token,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {}
                );

        System.out.println(objeto);
    }
}
