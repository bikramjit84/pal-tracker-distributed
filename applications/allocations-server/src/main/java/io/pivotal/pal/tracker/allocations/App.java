package io.pivotal.pal.tracker.allocations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestOperations;

import java.util.TimeZone;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import  org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.client.RestOperations;

@EnableWebSecurity
@EnableResourceServer
@EnableOAuth2Client
@EnableEurekaClient
@SpringBootApplication
@ComponentScan({"io.pivotal.pal.tracker.allocations", "io.pivotal.pal.tracker.restsupport"})
public class App {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(App.class, args);
    }
/*
    @Value("${application.oauth-enabled}")
    public String val;

    @Bean
    @LoadBalanced
    @ConditionalOnProperty(value = "application.oauth-enabled", havingValue = "false")
    public RestOperations restOperations() {

        System.out.println("TEMPLSAAAAAAAAAAAAAAAA = " + val);
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    @ConditionalOnProperty(value = "application.oauth-enabled", matchIfMissing = true)
    public RestOperations restTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext oauth2ClientContext) {

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& = " + val);
        return new OAuth2RestTemplate(resource, oauth2ClientContext);
    }

 */

    @Bean
    ProjectClient projectClient(
        RestOperations restOperations,
        @Value("${registration.server.endpoint}") String registrationEndpoint
    ) {
        return new ProjectClient(restOperations, registrationEndpoint);
    }
}
