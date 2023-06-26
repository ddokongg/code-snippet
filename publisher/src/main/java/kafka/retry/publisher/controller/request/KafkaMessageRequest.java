package kafka.retry.publisher.controller.request;

import lombok.Data;

@Data
public class KafkaMessageRequest {
    private String message;
}
