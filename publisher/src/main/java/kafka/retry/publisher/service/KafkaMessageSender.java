package kafka.retry.publisher.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class KafkaMessageSender {
    private static final long DEFAULT_TIMEOUT = 1;

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    public String send(ProducerRecord<Integer, String> producerRecord) throws Exception {
        try {
            return kafkaTemplate.send(producerRecord)
                    .get(DEFAULT_TIMEOUT, TimeUnit.SECONDS).toString();
        } catch (ExecutionException | TimeoutException | InterruptedException e) {
            // Do nothing
            throw new Exception(e);
        }
    }
}
