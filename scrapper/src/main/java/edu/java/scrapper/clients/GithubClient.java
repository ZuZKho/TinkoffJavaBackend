package edu.java.scrapper.clients;

import edu.java.scrapper.responses.github.GithubRepositoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GithubClient {

    private final WebClient webClient;

    @Value("${api.github.baseUrl}")
    private String baseUrl;

    public GithubClient(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public GithubClient() {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<GithubRepositoryResponse> getRepoByUser(String username, String repo) {
        return this.webClient.get().uri("/repos/{username}/{repo}", username, repo).retrieve().bodyToMono(
            GithubRepositoryResponse.class);
    }

}
