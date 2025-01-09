package study.springkubebatch;

import java.util.HashMap;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class HelloTaskBatch {

    @Bean
    public ItemReader<String> taskReader(StringRedisTemplate redisTemplate) {
        return () -> redisTemplate.opsForSet().pop("hello:task-queue");
    }

    @Bean
    public ItemProcessor<String, String> taskProcessor() {
        return task -> task + "/" + "Calculated by batch " + task;
    }

    @Bean
    public ItemWriter<String> taskWriter(StringRedisTemplate redisTemplate) {
        return items -> {
            HashMap<String, String> resultMap = new HashMap<>();
            for (String item : items) {
                String[] splat = item.split("/");
                String key = splat[0];
                String value = splat[1];
                resultMap.put(key, value);
            }

            redisTemplate.opsForHash().putAll("hello:result-set", resultMap);
        };
    }

    @Bean
    public Step helloStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ItemReader<String> taskReader,
            ItemProcessor<String, String> taskProcessor,
            ItemWriter<String> taskWriter) {
        return new StepBuilder("helloStep", jobRepository)
                .<String, String>chunk(10, transactionManager)
                .reader(taskReader)
                .processor(taskProcessor)
                .writer(taskWriter)
                .build();
    }

    @Bean
    public Job helloTaskJob(Step helloStep, JobRepository jobRepository) {
        return new JobBuilder("helloTaskJob", jobRepository)
                .start(helloStep)
                .build();
    }

}
