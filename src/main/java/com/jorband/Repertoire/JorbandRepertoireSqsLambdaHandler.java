package com.jorband.Repertoire;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.jorband.Repertoire.services.SongUsageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
public class JorbandRepertoireSqsLambdaHandler implements RequestHandler<SQSEvent, Void> {
    static ApplicationContext context = SpringApplication.run(JorbandRepertoireApplication.class);

    private final SongUsageService songUsageSqsService;

    public JorbandRepertoireSqsLambdaHandler(){
        songUsageSqsService = context.getBean(SongUsageService.class);
    }
    @Override
    public Void handleRequest(SQSEvent sqsEvent, Context context) {
        for (SQSEvent.SQSMessage message : sqsEvent.getRecords()) {
            log.info("Message Received in handler: {}", message.getBody());
            songUsageSqsService.processMessage(message.getBody());
        }
        return null;
    }

}


