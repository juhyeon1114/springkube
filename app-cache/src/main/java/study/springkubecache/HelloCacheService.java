package study.springkubecache;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloCacheService {

    private final HelloService helloService;
    private final Map<String, String> helloMap = new ConcurrentHashMap<>();

    public String getHello(String name) {
        String cachedHello = helloMap.get(name);

        if (Objects.nonNull(cachedHello)) {
            return cachedHello;
        } else {
            String hello = helloService.getHello(name);
            helloMap.put(name, hello);
            return hello;
        }
    }

}
