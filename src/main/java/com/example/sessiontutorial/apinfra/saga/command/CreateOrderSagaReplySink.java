package com.example.sessiontutorial.apinfra.saga.command;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CreateOrderSagaReplySink {

    String CREATE_ORDER_SAGA_REPLY_INPUT = "createOrderSagaReplyInput";

    @Input(CreateOrderSagaReplySink.CREATE_ORDER_SAGA_REPLY_INPUT)
    SubscribableChannel createOrderSagaReplyInput();
}
