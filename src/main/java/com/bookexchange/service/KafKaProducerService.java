package com.bookexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class KafKaProducerService
{
    private static final Logger logger =
            Logger.getLogger(KafKaProducerService.class.getName());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message)
    {
        logger.info(String.format("Message sent -> %s", message));
        this.kafkaTemplate.send(AppConstants.TOPIC_NAME, message);
    }
}
