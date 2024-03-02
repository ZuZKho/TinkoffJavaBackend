package edu.java.scrapper.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.java.scrapper.responses.stackoverflow.StackoverflowQuestionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class StackoverflowClient {

    private final WebClient webClient;

    @Value("${api.stackoverflow.baseUrl")
    private String baseUrl;

    public StackoverflowClient(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public StackoverflowClient() {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<StackoverflowQuestionResponse> getQuestionById(long id) {
        return this.webClient.get().uri("/questions/{id}?site=stackoverflow.com", id).retrieve()
            .bodyToMono(String.class)
            .map(response -> {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new JavaTimeModule());

                    JsonNode jsonNode = objectMapper.readTree(response);
                    JsonNode itemsNode = jsonNode.get("items");
                    JsonNode firstItemNode = itemsNode.get(0);
                    return objectMapper.treeToValue(firstItemNode, StackoverflowQuestionResponse.class);
                } catch (Exception e) {
                    return null;
                }
            });
    }

}
