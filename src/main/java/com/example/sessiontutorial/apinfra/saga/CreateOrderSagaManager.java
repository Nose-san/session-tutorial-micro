package com.example.sessiontutorial.apinfra.saga;

import com.example.sessiontutorial.apinfra.exception.BusinessException;
import com.example.sessiontutorial.apinfra.exception.ScheduleFullException;
import com.example.sessiontutorial.apinfra.exception.SystemException;
import com.example.sessiontutorial.apinfra.saga.command.CreateOrderSagaReplySink;
import com.example.sessiontutorial.apinfra.saga.command.DeliveryCommandSource;
import com.example.sessiontutorial.apinfra.saga.command.common.Failure;
import com.example.sessiontutorial.apinfra.saga.command.common.Outcome;
import com.example.sessiontutorial.apinfra.saga.command.common.ReplyMessageHeaders;
import com.example.sessiontutorial.apinfra.saga.command.common.Success;
import com.example.sessiontutorial.domain.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Locale;

@EnableBinding(CreateOrderSagaReplySink.class)
@Component
public class CreateOrderSagaManager {

    @Autowired
    MessageSource messageSource;


    @Autowired
    CreateOrderSaga createOrderSaga;

    public void create (CreateOrderSagaState createOrderSagaState) throws BusinessException{
        List<SagaParticipant> sagaDefinition = createOrderSaga.getSagaDefinition();
        Deque<SagaParticipant> sagaParticipantDoneList = new ArrayDeque<>();

        for (SagaParticipant sagaParticipant : sagaDefinition){
            try{
                sagaParticipant.forwardTransaction(createOrderSagaState);
                sagaParticipantDoneList.push(sagaParticipant);
                System.out.print("################################");
                System.out.print(sagaParticipant);
                System.out.print("################################");

            }catch (BusinessException e){
                sagaParticipantDoneList.pop().compensatingTransaction(createOrderSagaState);
                throw e;
            }

        }
    }

}
