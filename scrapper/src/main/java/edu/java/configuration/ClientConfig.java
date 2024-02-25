package edu.java.configuration;

import edu.java.clients.GithubClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    String baseUrl;

    @Bean
    public GithubClient githubClient(String baseUrl) {
        this.baseUrl = baseUrl;
        return new GithubClient(WebClient.builder(), baseUrl);
    }
//
//    @Bean
//    StackOverflowClient();
}
