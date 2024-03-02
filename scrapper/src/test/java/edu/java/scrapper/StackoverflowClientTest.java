package edu.java.scrapper;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import edu.java.scrapper.clients.StackoverflowClient;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class StackoverflowClientTest {

    @Test
    void test() throws IOException {
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
        WireMock.stubFor(WireMock.get(urlEqualTo("/questions/50617120?site=stackoverflow.com")).willReturn(aResponse()
            .withHeader("Content-Type", "application/json")
            .withBody(Files.readString(Paths.get("src/test/resources/stackoverflowQuestionResponse.json")))));

        var client = new StackoverflowClient(wireMockServer.baseUrl());

        StepVerifier.create(client.getQuestionById(50617120))
            .assertNext(response -> {
                Assertions.assertEquals(50617120, response.getId());
                Assertions.assertEquals(
                    "https://stackoverflow.com/questions/50617120/how-to-write-text-or-put-image-on-top-of-3d-cube-in-opengl-java-in-android-obje",
                    response.getLink()
                );
                Assertions.assertEquals("Jay Patel", response.getOwner().getName());
                Assertions.assertEquals("2018-06-08T18:22:05Z", response.getLastActivityDate().toString());
            })
            .verifyComplete();
    }

}
