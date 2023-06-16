package sustenapp_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "SustenApp - API",
                version = "1.0.0",
                description = "",
                // termsOfService = "",

                contact = @Contact(
                        name = "Kaique Souza Santos",
                        email = "kaiquesouzasantos905@gmail.com"
                ),

                license = @License(
                        name = "license",
                        url = ""
                )
        )
)

@SpringBootApplication
public class SustenAppApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SustenAppApiApplication.class, args);
    }
}