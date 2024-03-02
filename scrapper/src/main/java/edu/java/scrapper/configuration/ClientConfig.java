package edu.java.scrapper.configuration;

import edu.java.scrapper.clients.GithubClient;
import edu.java.scrapper.clients.StackoverflowClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public GithubClient githubClient() {
        return new GithubClient();
    }

    @Bean
    public StackoverflowClient stackoverflowClient() {
        return new StackoverflowClient();
    }

}
