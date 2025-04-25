package co.apna.path_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeneratePathResponseDTO (
    @JsonProperty("pathTitle")
    @JsonPropertyDescription("Title of learning path")
    String pathTitle,

    @JsonProperty("description")
    @JsonPropertyDescription("Description of the learning path")
    String description,

    @JsonProperty("estimatedDuration")
    @JsonPropertyDescription("Total duration of tge learning path")
    String estimatedDuration,

    @JsonProperty("steps")
    @JsonPropertyDescription("List of steps needed to complete the learning path" +
            "each step has stepNumber (Integer), title (String), description(String)" +
            "and duration (String)")
    List<Step> steps
) {
}
