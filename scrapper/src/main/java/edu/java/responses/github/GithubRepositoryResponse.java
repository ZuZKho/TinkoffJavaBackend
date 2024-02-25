package edu.java.responses.github;

import org.springframework.stereotype.Component;

@Component
public class GithubRepositoryResponse {
    Long id;
    String fullName;
    String description;
    String url;
}
