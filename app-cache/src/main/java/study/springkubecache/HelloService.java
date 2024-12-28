package study.springkubecache;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class HelloService {

    private final RestClient restClient = RestClient.create();

    public String getHello(String name) {
        return restClient.get()
                .uri("http://springkube-app-service:8080/hello?name=" + name)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new RuntimeException("Failed to get hello message");
                })
                .body(String.class);
    }

}
