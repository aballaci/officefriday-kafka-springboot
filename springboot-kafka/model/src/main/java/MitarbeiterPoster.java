import com.google.gson.Gson;
import com.springboot.model.Mitarbeiter;
import com.springboot.model.ModelGenerator;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.stream.IntStream;

public class MitarbeiterPoster {

    static URI uri;

    static {
        try {
            uri = new URI("http://localhost:31077/kafkaProducer");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws InterruptedException {
        int userId = 0;
        while (true) {
            Mitarbeiter m = ModelGenerator.generateMitarbeiter(userId);
            String message = new Gson().toJson(m);
            postJSON(message);
            userId++;
            Thread.sleep(1200);
        }

    }

    public static void postJSON(String requestBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            System.out.println("sending: " + requestBody);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
