package study.springkubecache.healthcheck;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/probe")
public class HealthCheckController {

    @GetMapping("/startup")
    public String startup() {
        log.info("startup");
        return "OK";
    }

    @GetMapping("/ready")
    public String ready() {
        log.info("ready");
        return "OK";
    }

    @GetMapping("/live")
    public String live() {
        log.info("live");
        return "OK";
    }

}
