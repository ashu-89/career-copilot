package co.apna.path_service.service;

import co.apna.path_service.dto.GeneratePathResponseDTO;
import co.apna.path_service.dto.Goal;

public interface PathGenerationService {
    public GeneratePathResponseDTO generateLearningPath(Goal goal);
}
