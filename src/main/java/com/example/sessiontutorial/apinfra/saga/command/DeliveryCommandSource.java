package com.example.sessiontutorial.apinfra.saga.command;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface DeliveryCommandSource {
    String DELIVERY_COMMAND_OUTPUT = "deliveryCommandOutput";

    @Output(DeliveryCommandSource.DELIVERY_COMMAND_OUTPUT)
    MessageChannel deliveryCommandOutput();
}
