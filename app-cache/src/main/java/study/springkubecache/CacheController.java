package study.springkubecache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class CacheController {

    @Value("${appProperty.language}")
    private String language;

    @Value("${appProperty.apiKey}")
    private String apiKey;


    @GetMapping("/{name}")
    public String hello(@PathVariable("name") String name, @RequestParam("key") String key) {
        if (!apiKey.equals(key)) {
            throw new IncorroctApiKeyException("API Key is incorrect");
        }

        if (language.equals("ko")) {
            return "안녕 " + name;
        } else {
            return "Hello " + name;
        }
    }

}
