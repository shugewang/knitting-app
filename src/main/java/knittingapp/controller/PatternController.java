package knittingapp.controller;

import knittingapp.repository.Pattern;
import knittingapp.service.PatternService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PatternController {
    private final PatternService patternService;

    public PatternController(PatternService patternService) {
        this.patternService = patternService;
    }

    @GetMapping("/patterns")
    public ResponseEntity<List<Pattern>> getPatterns() {
        return ResponseEntity.ok(patternService.getAllPatterns());
    }
}
