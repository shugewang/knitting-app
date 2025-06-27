package knittingapp.controller;

import jakarta.validation.Valid;
import knittingapp.controller.model.RavelryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

@Controller
public class TestController {

    private final RestTemplateBuilder restTemplateBuilder;

    @Value("${ravelry.username}")
    private String username;

    @Value("${ravelry.password}")
    private String password;

    @Autowired
    public TestController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @GetMapping("/")
    public ResponseEntity index(@Valid @ModelAttribute RavelryRequest request) {
        RestTemplate restTemplate = restTemplateBuilder.basicAuthentication(username, password).build();

        try {
            String url = String.format(
                    "https://api.ravelry.com/patterns/search.json?query=%s",
                    request.category()
            );

            String text = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(text);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error fetching data: " + e.getMessage());
        }
    }

}
