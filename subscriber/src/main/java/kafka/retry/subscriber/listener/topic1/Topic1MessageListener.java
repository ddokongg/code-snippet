package kafka.retry.subscriber.listener.topic1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Topic1MessageListener {
    @RetryableTopic(attempts = "5")
    @KafkaListener(
            id = Topic1Constants.TOPIC_NAME,
            groupId = Topic1Constants.CONSUMER_GROUP,
            topics = Topic1Constants.TOPIC_NAME)
    public void listen(String data) throws KafkaException {
        log.info(Topic1Constants.TOPIC_NAME + " published: " + data);

        throw new RuntimeException("Exception Raised");
    }
}
