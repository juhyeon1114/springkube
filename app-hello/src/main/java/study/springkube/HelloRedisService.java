package study.springkube;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HelloRedisService {

    private final StringRedisTemplate stringRedisTemplate;

    @Scheduled(fixedDelay = 1000L)
    public void dequeueHello() {
        if (!stringRedisTemplate.hasKey("hello:task-queue")) {
            log.info("No task in queue");
            return;
        }

        log.info("Dequeue Start");
        String task = stringRedisTemplate.opsForSet().pop("hello:task-queue");
        if (Objects.nonNull(task)) {
            stringRedisTemplate.opsForHash()
                    .put("hello:result-set", task, "Calculated " + task);
        }
    }

}
