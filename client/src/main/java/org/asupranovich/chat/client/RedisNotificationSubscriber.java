package org.asupranovich.chat.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

@Slf4j
public class RedisNotificationSubscriber implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            log.info("New message received: {}", message);
        } catch (Exception e) {
            log.error("Message receive failed");
        }
    }
}
