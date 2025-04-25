package co.apna.path_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Step(
        @JsonProperty("stepNumber")
        @JsonPropertyDescription("Serial number of step")
        Integer stepNumber,

        @JsonProperty("title")
        @JsonPropertyDescription("Title of the step")
        String title,

        @JsonProperty("description")
        @JsonPropertyDescription("Description of the step")
        String description,

        @JsonProperty("duration")
        @JsonPropertyDescription("Duration of the step")
        String duration) {
}
