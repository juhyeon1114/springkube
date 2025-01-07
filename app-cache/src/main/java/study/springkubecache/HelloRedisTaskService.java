package study.springkubecache;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloRedisTaskService {

    private final StringRedisTemplate stringRedisTemplate;

    public long enqueue(String name) {
        stringRedisTemplate.opsForSet().add("hello:task-queue", name);
        Long size = stringRedisTemplate.opsForSet().size("hello:task-queue");
        return Objects.isNull(size) ? 0 : size;
    }

}
