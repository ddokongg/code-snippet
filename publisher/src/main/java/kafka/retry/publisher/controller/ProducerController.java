package kafka.retry.publisher.controller;

import kafka.retry.publisher.controller.request.KafkaMessageRequest;
import kafka.retry.publisher.service.KafkaMessageSender;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {
    private final KafkaMessageSender kafkaMessageSender;

    @PostMapping("/topics/{topicId}/publish")
    public ResponseEntity<String> publish(@PathVariable("topicId") String topicId, @RequestBody KafkaMessageRequest request) {
        try {
            String publishedResponse = kafkaMessageSender.send(new ProducerRecord<>(topicId, request.getMessage()));

            return ResponseEntity.ok(publishedResponse);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
