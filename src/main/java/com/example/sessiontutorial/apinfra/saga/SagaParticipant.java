package com.example.sessiontutorial.apinfra.saga;

import com.example.sessiontutorial.apinfra.exception.BusinessException;

public interface SagaParticipant {
    //void forwardTransaction(SagaState sagaState);
    //void compensatingTransaction(SagaState sagaState);

    void forwardTransaction(CreateOrderSagaState createOrderSagaState) throws BusinessException;
    void compensatingTransaction(CreateOrderSagaState createOrderSagaState) throws BusinessException;
}
