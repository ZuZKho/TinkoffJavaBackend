package edu.java.scrapper.responses.github;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GithubRepositoryResponse {
    Long id;
    String fullName;
    String description;
    String url;
    OffsetDateTime createdAt;
}
