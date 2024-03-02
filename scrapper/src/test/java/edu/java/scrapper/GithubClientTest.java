package edu.java.scrapper;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import edu.java.scrapper.clients.GithubClient;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class GithubClientTest {

    @Test
    void test() throws IOException {
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
        WireMock.stubFor(WireMock.get(urlEqualTo("/repos/ZuZKho/OOP")).willReturn(aResponse()
            .withHeader("Content-Type", "application/json")
            .withBody(Files.readString(Paths.get("src/test/resources/githubRepoResponse.json")))));

        var client = new GithubClient(wireMockServer.baseUrl());

        StepVerifier.create(client.getRepoByUser("ZuZKho", "OOP"))
            .assertNext(response -> {
                Assertions.assertEquals("ZuZKho/OOP", response.getFullName());
                Assertions.assertEquals(687412560, response.getId());
                Assertions.assertEquals("https://api.github.com/repos/ZuZKho/OOP", response.getUrl());
                Assertions.assertEquals("2023-09-05T10:00:22Z", response.getCreatedAt().toString());
            })
            .verifyComplete();
    }
}
