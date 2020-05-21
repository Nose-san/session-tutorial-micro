package com.example.sessiontutorial.domain.service.delivery;

import com.example.sessiontutorial.apinfra.exception.BusinessException;
import com.example.sessiontutorial.apinfra.saga.command.CancelDeliveryCommand;
import com.example.sessiontutorial.apinfra.saga.command.ScheduleDeliveryCommand;
import com.example.sessiontutorial.domain.model.Delivery;
import com.example.sessiontutorial.domain.model.Order;
import com.example.sessiontutorial.domain.repository.delivery.DeliveryRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    Mapper beanMapper;

    public void schedule(Order order) throws BusinessException {
        ScheduleDeliveryCommand scheduleDeliveryCommand = beanMapper.map(order, ScheduleDeliveryCommand.class);
        deliveryRepository.send(scheduleDeliveryCommand);
    }

    public void cancel(Order order) throws BusinessException {
        CancelDeliveryCommand cancelDeliveryCommand = beanMapper.map(order, CancelDeliveryCommand.class);
        deliveryRepository.send(cancelDeliveryCommand);
    }
}
