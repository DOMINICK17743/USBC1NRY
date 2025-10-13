// 代码生成时间: 2025-10-13 20:09:35
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class AutoCompleteService {

    private final RestTemplate restTemplate;
    private final AutoCompleteClient autoCompleteClient;

    @Autowired
    public AutoCompleteService(RestTemplate restTemplate, AutoCompleteClient autoCompleteClient) {
        this.restTemplate = restTemplate;
        this.autoCompleteClient = autoCompleteClient;
    }

    @GetMapping("/search")
    public List<String> search(@RequestParam("q") String query) {
        try {
            // Call the autoCompleteClient service to get suggestions
            return autoCompleteClient.getSuggestions(query);
        } catch (Exception e) {
            // Handle any exceptions that may occur
            return List.of("Error: Unable to retrieve suggestions");
        }
    }
}

@FeignClient(name = "autocomplete-service", url = "http://autocomplete-service")
interface AutoCompleteClient {
    // Define the method to get suggestions from the autocomplete service
    @GetMapping("/suggestions")
    List<String> getSuggestions(String query);
}