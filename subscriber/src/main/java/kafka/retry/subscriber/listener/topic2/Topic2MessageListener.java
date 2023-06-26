package kafka.retry.subscriber.listener.topic2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.SameIntervalTopicReuseStrategy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Topic2MessageListener {
    @RetryableTopic(
            attempts = "5",
            sameIntervalTopicReuseStrategy = SameIntervalTopicReuseStrategy.SINGLE_TOPIC
    )
    @KafkaListener(
            id = Topic2Constants.TOPIC_NAME,
            groupId = Topic2Constants.CONSUMER_GROUP,
            topics = Topic2Constants.TOPIC_NAME)
    public void listen(String data) throws KafkaException {
        log.info(Topic2Constants.TOPIC_NAME + " published: " + data);

        throw new RuntimeException("Exception Raised");
    }
}
