package edu.java.clients;

import edu.java.responses.github.GithubRepositoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GithubClient {

    private final WebClient webClient;


    public GithubClient(WebClient.Builder webClientBuilder, String baseUrl) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").build();
    }

    public Mono<GithubRepositoryResponse> GetRepoByUser(String username, String repo) {
        return this.webClient.get().uri("/repos/{username}/{repo}/", username, repo).retrieve().bodyToMono(
            GithubRepositoryResponse.class);
    }


}
