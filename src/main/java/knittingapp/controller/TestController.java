package knittingapp.controller;

import jakarta.validation.Valid;
import knittingapp.controller.model.RavelryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

@Controller
public class TestController {

    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public TestController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @GetMapping("/")
    public ResponseEntity index(@Valid @ModelAttribute RavelryRequest request) {
        String username = "aef9bffa1b0be7a921b0ac3532bca574";
        String password = "0_sMvoUxuaZjgKzT9g1-vNm7N1nosTMhUdWh2Hzx";

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
