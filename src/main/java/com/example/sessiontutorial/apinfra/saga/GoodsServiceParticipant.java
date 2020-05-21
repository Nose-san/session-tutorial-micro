package com.example.sessiontutorial.apinfra.saga;

import com.example.sessiontutorial.apinfra.exception.BusinessException;
import com.example.sessiontutorial.domain.model.OrderLine;
import com.example.sessiontutorial.domain.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("goodsServiceParticipant")
public class GoodsServiceParticipant implements SagaParticipant {

    @Autowired
    GoodsService goodsService;

    @Override
    public void forwardTransaction(CreateOrderSagaState createOrderSagaState) throws BusinessException {
        for (OrderLine orderLine : createOrderSagaState.getOrderLines()) {
            goodsService.decreaseStock(orderLine.getGoods(), orderLine.getQuantity());
        }
    }

    @Override
    public void compensatingTransaction(CreateOrderSagaState createOrderSagaState) throws BusinessException{
        for (OrderLine orderLine : createOrderSagaState.getOrderLines()) {
            goodsService.increaseStock(orderLine.getGoods(), orderLine.getQuantity());
        }
    }
}