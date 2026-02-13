package AgroTrackpesagem.demo.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Controle de Gado")
                        .version("v1")
                        .description("API para gerenciamento e controle de engorda")
                        .termsOfService("https://www.linkedin.com/in/felipe-pacheco-642270297/")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.linkedin.com/in/felipe-pacheco-642270297/")));
    }
}
