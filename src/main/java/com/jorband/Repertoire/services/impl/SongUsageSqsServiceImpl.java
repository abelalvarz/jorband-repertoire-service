package com.jorband.Repertoire.services.impl;

import com.google.gson.Gson;
import com.jorband.Repertoire.dtos.request.SongUsageRequest;
import com.jorband.Repertoire.services.RepertoireService;
import com.jorband.Repertoire.services.SongUsageService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SongUsageSqsServiceImpl implements SongUsageService {

    private final RepertoireService repertoireService;
    private  final Logger logger = LoggerFactory.getLogger(SongUsageSqsServiceImpl.class);
    private  final Gson gson = new Gson();

    @Override
    public void processMessage(String message) {

        try{
            SongUsageRequest songs = gson.fromJson(message, SongUsageRequest.class);
            String receivedMessage = gson.toJson(songs);

            logger.info("Received message: {}", receivedMessage);
            repertoireService.updateSongUsage(songs);
        } catch (Exception e){
            logger.error("Error processing message, cause: {}", e.getMessage());
            throw new RuntimeException("Error processing message, cause: ", e);
        }
    }
}
