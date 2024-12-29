package study.springkube;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    private void delay(int ms) {
        long elapsedTime;
        long startTime = System.currentTimeMillis();
        do {
            elapsedTime = System.currentTimeMillis() - startTime;
        } while (elapsedTime < ms);
    }

    @GetMapping
    public String helloName(@RequestParam("name") String name) {
        log.info("name: {}", name);
        delay(500);
        return Objects.nonNull(name)
                ? "Hello, " + name + "!"
                : "Hello, World!";
    }

}
