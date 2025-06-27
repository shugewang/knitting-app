package knittingapp.service;

import knittingapp.repository.Pattern;
import knittingapp.repository.PatternRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatternService {

    private final PatternRepository patternRepository;

    public List<Pattern> getAllPatterns() {
        return patternRepository.findAll();
    }
}
