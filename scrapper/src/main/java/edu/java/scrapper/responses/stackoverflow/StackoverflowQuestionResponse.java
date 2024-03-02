package edu.java.scrapper.responses.stackoverflow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class StackoverflowQuestionResponse {
    @JsonProperty("question_id")
    Long id;
    String link;
    Owner owner;

    @JsonProperty("last_activity_date")
    OffsetDateTime lastActivityDate;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Owner {
        @JsonProperty("display_name")
        String name;
    }
}
