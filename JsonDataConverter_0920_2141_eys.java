// 代码生成时间: 2025-09-20 21:41:22
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import java.io.IOException;
import java.util.Map;

/**
 * JsonDataConverter is a service class that handles JSON data conversion.
 */
@Service
public class JsonDataConverter {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public JsonDataConverter(ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    /**
     * Converts JSON data from one format to another.
     * @param jsonInput The input JSON string.
     * @param url The URL of the converter service.
     * @return The converted JSON string.
     * @throws IOException If there is an error during JSON parsing or network IO.
     */
    public String convertJsonData(String jsonInput, String url) throws IOException {
        try {
            // Convert the input JSON string to a Map for POSTing to the converter service
            Map<String, Object> jsonMap = objectMapper.readValue(jsonInput, Map.class);

            // Send the JSON map to the conversion service
            ResponseEntity<String> response = restTemplate.exchange(
                url, 
                HttpMethod.POST, 
                null, 
                String.class, 
                jsonMap
            );

            // Return the converted JSON string
            return response.getBody();
        } catch (Exception e) {
            // Handle exceptions and throw IOException to keep method signature clean
            throw new IOException("Error converting JSON data.", e);
        }
    }
}
