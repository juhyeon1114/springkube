package study.springkubecache;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class CacheController {

    private final HelloRedisTaskService helloTaskService;
    private final HelloService helloService;
    private final HelloRedisCacheService helloRedisCacheService;

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

    @GetMapping("/enqueue/{name}")
    public String helloEnqueue(@PathVariable("name") String name) {
        helloTaskService.enqueue(name);
        if (language.equals("ko")) {
            return "작업 예약 : " + name;
        } else {
            return "Task is enqueued : " + name;
        }
    }

    @GetMapping("/redis/{name}")
    public String helloRedis(@PathVariable("name") String name) {
        return helloRedisCacheService.cachedHello(name);
    }

}
