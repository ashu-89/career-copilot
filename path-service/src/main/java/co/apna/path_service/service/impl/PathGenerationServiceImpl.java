package co.apna.path_service.service.impl;

import co.apna.path_service.service.PathGenerationService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class PathGenerationServiceImpl implements PathGenerationService {

    private final ChatModel chatModel;

    public PathGenerationServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }


    @Override
    public String generateLearningPath(String goal) {
        PromptTemplate promptTemplate = new PromptTemplate(goal);
        Prompt prompt = promptTemplate.create();

        ChatResponse chatResponse = chatModel.call(prompt);
        return chatResponse.getResult().getOutput().getText();
    }
}
