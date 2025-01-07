package study.springkube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringkubeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringkubeApplication.class, args);
    }

}
