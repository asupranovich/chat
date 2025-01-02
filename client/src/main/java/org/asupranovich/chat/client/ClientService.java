package org.asupranovich.chat.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final RestTemplate restTemplate;

    public String sendMessage(Message message) {
        ResponseEntity<String> response = restTemplate.postForEntity("http://server/messages", message, String.class);
        return response.getBody();
    }

    @KafkaListener(topics = "#{'${kafka.consumer.ack-topic}'}")
    public void listenWithHeaders(@Payload String message) {
        log.info("Received Message: {}", message);
    }

}
