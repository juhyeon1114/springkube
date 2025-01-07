package study.springkubecache;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloRedisCacheService {

    private final StringRedisTemplate stringRedisTemplate;

    public String cachedHello(String name) {
        Object o = stringRedisTemplate.opsForHash().get("hello:result-set", name);
        if (Objects.isNull(o)) {
            return null;
        } else {
            return (String) o;
        }
    }

    public void cacheHello(String name) {
        stringRedisTemplate.opsForHash().put("hello:result-set", name, "Hello " + name);
    }

}
