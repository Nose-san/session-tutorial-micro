package com.example.sessiontutorial.apinfra.saga.command.common;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class CommandHandlerReplyBuilder {


    private static <T> Message with(T reply, CommandReplyOutcome outcome) {
        MessageBuilder messageBuilder = MessageBuilder
                .withPayload(reply)
                .setHeader(ReplyMessageHeaders.REPLY_OUTCOME, outcome.name())
                .setHeader(ReplyMessageHeaders.REPLY_TYPE, reply.getClass().getName());
        return messageBuilder.build();
    }

    public static Message withSuccess(Object reply) {
        return with(reply, CommandReplyOutcome.SUCCESS);
    }

    public static Message withSuccess() {
        return withSuccess(new Success());
    }

    public static Message withFailure() {
        return withFailure(new Failure());
    }
    public static Message withFailure(Object reply) {
        return with(reply, CommandReplyOutcome.FAILURE);
    }

}
