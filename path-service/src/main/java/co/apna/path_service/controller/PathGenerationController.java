package co.apna.path_service.controller;

import co.apna.path_service.dto.GeneratePathResponseDTO;
import co.apna.path_service.dto.Goal;
import co.apna.path_service.service.PathGenerationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/path")
public class PathGenerationController {

    private final PathGenerationService pathGenerationService;

    public PathGenerationController(PathGenerationService pathGenerationService) {
        this.pathGenerationService = pathGenerationService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from Path Service!");
    }

    @PostMapping("/generate")
    public GeneratePathResponseDTO generate(@RequestBody Goal goal) {
        return pathGenerationService.generateLearningPath(goal);
    }
}
