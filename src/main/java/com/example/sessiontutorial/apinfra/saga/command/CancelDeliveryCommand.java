package com.example.sessiontutorial.apinfra.saga.command;

import com.example.sessiontutorial.apinfra.saga.command.common.Command;

import java.io.Serializable;

public class CancelDeliveryCommand implements Command, Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;
}
