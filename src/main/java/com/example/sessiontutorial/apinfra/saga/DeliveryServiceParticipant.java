package com.example.sessiontutorial.apinfra.saga;

import com.example.sessiontutorial.apinfra.exception.BusinessException;
import com.example.sessiontutorial.domain.model.Delivery;
import com.example.sessiontutorial.domain.model.OrderLine;
import com.example.sessiontutorial.domain.service.delivery.DeliveryService;
import com.example.sessiontutorial.domain.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;

public class DeliveryServiceParticipant implements SagaParticipant{

    @Autowired
    DeliveryService deliveryService;

    @Override
    public void forwardTransaction(CreateOrderSagaState createOrderSagaState) throws BusinessException {
        deliveryService.schedule(createOrderSagaState.getOrder());
    }

    @Override
    public void compensatingTransaction(CreateOrderSagaState createOrderSagaState) throws BusinessException{
        deliveryService.cancel(createOrderSagaState.getOrder());
    }
}
