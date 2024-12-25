package study.springkube;

import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String helloName(@RequestParam("name") String name) {
        return Objects.nonNull(name)
                ? "Hello, " + name + "!"
                : "Hello, World!";
    }

}
