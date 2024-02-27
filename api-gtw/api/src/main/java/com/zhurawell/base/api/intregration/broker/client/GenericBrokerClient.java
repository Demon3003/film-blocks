package com.zhurawell.base.api.intregration.broker.client;

import com.zhurawell.blocks.common.broker.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.binding.StreamListenerMessageHandler;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
public abstract class GenericBrokerClient {

    private StreamBridge streamBridge;

    public GenericBrokerClient(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    protected void sendMessage(String bindingName, Event event) {
        log.debug("Sending a {} message to {}", event.getEventType(), bindingName);
        Message message = MessageBuilder.withPayload(event)
                .setHeader("partitionKey", event.getKey())
                .build();
        try {
            streamBridge.send(bindingName, message);
        } catch (Exception ex) {
            log.error("Send message to topic {} failed, because {}", bindingName, ex);
        }
     
    }

}
