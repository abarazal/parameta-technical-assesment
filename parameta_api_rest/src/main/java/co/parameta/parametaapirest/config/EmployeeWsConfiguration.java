package co.parameta.parametaapirest.config;

import co.parameta.parametaapirest.client.EmployeeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class EmployeeWsConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("co.parameta.parametaapirest.wsdl");
        return marshaller;
    }

    @Bean
    public EmployeeClient countryClient(Jaxb2Marshaller marshaller) {
        EmployeeClient client = new EmployeeClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
