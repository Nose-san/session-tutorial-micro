package com.example.sessiontutorial.domain.repository.delivery;

import com.example.sessiontutorial.apinfra.exception.BusinessException;
import com.example.sessiontutorial.apinfra.exception.ScheduleFullException;
import com.example.sessiontutorial.apinfra.saga.command.CreateOrderSagaReplySink;
import com.example.sessiontutorial.apinfra.saga.command.DeliveryCommandSource;
import com.example.sessiontutorial.apinfra.saga.command.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import org.springframework.cloud.stream.annotation.EnableBinding;

import java.util.Locale;

@Component
@EnableBinding({DeliveryCommandSource.class, CreateOrderSagaReplySink.class})
public class DeliveryRepositoryImpl implements DeliveryRepository{

    @Autowired
    DeliveryCommandSource deliveryCommandSource;

    @Override
    public void send(Command command) {
        deliveryCommandSource.deliveryCommandOutput().send(MessageBuilder
                .withPayload(command)
                .setHeader(CommandMessageHeaders.COMMAND_TYPE, command.getClass().getSimpleName())
                .setHeader(CommandMessageHeaders.REPLY_TO, "createOrderSagaReplyOutput")
                .build());
    }


}
