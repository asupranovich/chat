package org.asupranovich.chat.server;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ServerService {

    private static final String CHAT_MESSAGES_KEY = "chat.messages";

    @Value("${kafka.producer.ack-topic}")
    private String ackTopic;

    @Autowired
    private ChannelTopic notificationTopic;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void storeMessage(Message message) {
        String messageText = message.getText();
        redisTemplate.opsForList().rightPush(CHAT_MESSAGES_KEY, messageText);
        kafkaTemplate.send(ackTopic, messageText, "Received msg: " + messageText);
        redisTemplate.convertAndSend(notificationTopic.getTopic(), "Message: '" + messageText + "' was received");
    }

    public List<Message> readAllMessages() {
        return redisTemplate.opsForList().range(CHAT_MESSAGES_KEY, 0, -1).stream().map(Message::new).toList();
    }

}
