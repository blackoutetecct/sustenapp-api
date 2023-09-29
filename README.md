<h1 align=center>SUSTENAPP-API</h1>

<p align="center">
  <img src="logo_sustenapp.png" width="500">
</p>

#
### TESTES

- <strong>UNITARIO</strong>:
```bash
cd sustenApp/src/test/java/sustenapp_api/unit
```

- <strong>INTEGRACAO</strong>:
```bash
cd sustenApp/src/test/java/sustenapp_api/integration
```

- <strong>CARGA</strong>:
```bash 
cd sustenApp/src/test/java/sustenapp_api/stress
```

#
### EXECUCAO DE TESTES DE CARGA
- Mova o diretorio de testes de carga para o pacote principal

```bash 
mv sustenApp/src/test/java/sustenapp_api/stress sustenApp/src/main/java/sustenapp_api/test/stress
```

- Renomeie o pacote contido nos arquivos `executor/SimulatorPersistEntity.java` e `util/FactoryEntity.java`

```java
package sustenapp_api.test.stress.executor/util;
```

- Realize as seguintes alteracoes nos arquivos respectivos:

```properties
# sustenApp/src/main/resources/application.properties

spring.datasource.username = postgres
spring.datasource.password = 123456789
spring.datasource.url = jdbc:postgresql://localhost:5432/postgres
spring.jpa.hibernate.ddl-auto = create-drop

spring.security.enabled=false
```

```java
// sustenApp/src/main/java/sustenapp_api/component/security/ConfigurationSecurity.java

[...]

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {
    private final FilterSecurity filterSecurity;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(withDefaults())
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/**").permitAll()
                )
                .headers().frameOptions().disable().and()
                .cors().disable()
                .csrf().disable()
                .formLogin().disable()
                .addFilterBefore(filterSecurity, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .build();
    }

    [...]
}
```

```java
// sustenApp/src/main/java/sustenapp_api/SustenAppApiApplication.java

[...]

import sustenapp_api.test.stress.executor.SimulatorPersistEntity;

@SpringBootApplication
@EnableRetry
@EnableScheduling
public class SustenAppApiApplication {
    @Autowired private SimulatorPersistEntity simulator;
    private static SimulatorPersistEntity simulatorStatic;

    @PostConstruct
    private void initialization() {
        simulatorStatic = simulator;
    }

    public static void main(String[] args) {
        SpringApplication.run(SustenAppApiApplication.class, args);
        simulatorStatic.runSimulationSaveTest();
    }
}
```

- Execute o teste de carga contido no `sustenApp\src\main\java\sustenapp_api\test\stress\test\BateriaTestesEstresse.jmx` atraves do JMeter.
    
#
### TECNOLOGIAS

![Java](https://img.shields.io/badge/Java-0D1117?style=for-the-badge&logo=openjdk&logoColor=white&labelColor=0D1117)&nbsp;
![Spring](https://img.shields.io/badge/Spring-0D1117?style=for-the-badge&logo=spring&logoColor=107C10&labelColor=0D1117)&nbsp;
![SpringBoot](https://img.shields.io/badge/Spring_Boot-0D1117?style=for-the-badge&logo=springboot&logoColor=239120&labelColor=0D1117)&nbsp;
![SpringSecurity](https://img.shields.io/badge/Spring_Security-0D1117?style=for-the-badge&logo=Spring-Security&logoColor=239120&labelColor=0D1117)&nbsp;
![Hibernate](https://img.shields.io/badge/Hibernate-0D1117?style=for-the-badge&logo=Hibernate&logoColor=239120&labelColor=0D1117)&nbsp;
![Swagger](https://img.shields.io/badge/Swagger-0D1117?style=for-the-badge&logo=Swagger&logoColor=85EA2D&labelColor=0D1117)&nbsp;
![Maven](https://img.shields.io/badge/apache_maven-0D1117?style=for-the-badge&logo=apachemaven&logoColor=E34F26&labelColor=0D1117)&nbsp;
![JUnit5](https://img.shields.io/badge/Junit5-0D1117?style=for-the-badge&logo=junit5&logoColor=25A162&labelColor=0D1117)&nbsp;
![POSTGRESQL](https://img.shields.io/badge/PostgreSQL-0D1117?style=for-the-badge&logo=postgresql&labelColor=0D1117)&nbsp;
![Email](https://img.shields.io/badge/Gmail-0D1117?style=for-the-badge&logo=gmail&logoColor=D14836&labelColor=0D1117)&nbsp;

#
### DOMINIO DA API

```
https://sustenapp-api-production.up.railway.app
```

#
### DOCUMENTACAO DA API

```
https://sustenapp-api-production.up.railway.app/swagger-ui/index.html
```
