package com.example.sessiontutorial.apinfra.saga.command;

import com.example.sessiontutorial.apinfra.saga.command.common.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ScheduleDeliveryCommand implements Command, Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;

    private String email;

    private Date orderDate;

}
