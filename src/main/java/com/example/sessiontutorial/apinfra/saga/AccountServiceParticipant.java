package com.example.sessiontutorial.apinfra.saga;

import com.example.sessiontutorial.apinfra.exception.BusinessException;
import com.example.sessiontutorial.domain.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("accountServiceParticipant")
public class AccountServiceParticipant implements SagaParticipant{

    @Autowired
    AccountService accountService;

    @Override
    public void forwardTransaction(CreateOrderSagaState createOrderSagaState) throws BusinessException {
        accountService.addCreditCharge(createOrderSagaState.getAccount(), createOrderSagaState.getOrder().getTotalAmount());
    }

    @Override
    public void compensatingTransaction(CreateOrderSagaState createOrderSagaState) throws BusinessException{
        System.out.print("#############################");
        System.out.print(createOrderSagaState.getOrder().getTotalAmount());
        System.out.print("#############################");
        accountService.substractCreditCharge(createOrderSagaState.getAccount(), createOrderSagaState.getOrder().getTotalAmount());
    }
}
