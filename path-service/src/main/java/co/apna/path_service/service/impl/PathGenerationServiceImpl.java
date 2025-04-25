package co.apna.path_service.service.impl;

import co.apna.path_service.dto.GeneratePathResponseDTO;
import co.apna.path_service.dto.Goal;
import co.apna.path_service.service.PathGenerationService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class PathGenerationServiceImpl implements PathGenerationService {

    private final ChatModel chatModel;
    private final ObjectMapper objectMapper;

    public PathGenerationServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
        this.objectMapper = new ObjectMapper(); // or inject via constructor if needed
    }

    @Value("classpath:templates/generate-path-prompt.st")
    private Resource generatePathPrompt;

    @Override
    public GeneratePathResponseDTO generateLearningPath(Goal goal) {
        log.info("Received request to generate learning path for goal: {}", goal.goal());

        BeanOutputConverter<GeneratePathResponseDTO> converter =
                new BeanOutputConverter<>(GeneratePathResponseDTO.class);
        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(generatePathPrompt);
        Prompt prompt = promptTemplate.create(Map.of("goal", goal.goal(), "format", format));

        log.info("Final prompt content:\n{}", prompt.getContents());

        ChatResponse response = chatModel.call(prompt);
        String output = response.getResult().getOutput().getText();

        log.info("Raw response received from LLM:\n{}", output);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode fullTree = objectMapper.readTree(output);

            JsonNode justProperties = fullTree.get("properties");
            if (justProperties == null) {
                log.error("No 'properties' field found in LLM response. Cannot parse.");
                return null;
            }

            log.debug("Extracted 'properties' node:\n{}", justProperties.toPrettyString());

            GeneratePathResponseDTO dto = objectMapper.treeToValue(justProperties, GeneratePathResponseDTO.class);
            log.info("Successfully converted response to DTO: {}", dto);
            return dto;
        } catch (Exception e) {
            log.error("Error while converting LLM output to DTO", e);
            return null;
        }
    }



}
