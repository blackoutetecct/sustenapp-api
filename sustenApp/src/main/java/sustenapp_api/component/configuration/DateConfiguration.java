package sustenapp_api.component.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;

@Configuration
public class DateConfiguration {
    @Bean
    public ZoneId zoneId() {
        return ZoneId.of(System.getProperty("spring.timezone", "America/Sao_Paulo"));
    }
}
