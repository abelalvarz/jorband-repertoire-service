package com.jorband.Repertoire.configs;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.jorband.Repertoire.services.SongUsageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class SqsLambdaConsumerConfig {

    @Bean
    public Consumer<SQSEvent> sqsMessageConsumer(SongUsageService songUsageSqsService){
        return event -> {
            for (SQSEvent.SQSMessage message : event.getRecords()){
                songUsageSqsService.SQSMessageListener(message.getBody());
            }
        };
    }
}
