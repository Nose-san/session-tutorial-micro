package com.example.sessiontutorial.config;

import com.example.sessiontutorial.apinfra.saga.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SagaConfig {

    @Bean
    public CreateOrderSaga createOrderSaga(OrderServiceParticipant orderServiceParticipant,
                                           GoodsServiceParticipant goodsServiceParticipant,
                                           AccountServiceParticipant accountServiceParticipant){
        CreateOrderSaga createOrderSaga = new CreateOrderSaga();
        List<SagaParticipant> sagaDefinition = new ArrayList<>();
        sagaDefinition.add(orderServiceParticipant);
        sagaDefinition.add(goodsServiceParticipant);
        sagaDefinition.add(accountServiceParticipant);
        createOrderSaga.setSagaDefinition(sagaDefinition);
        return createOrderSaga;
    }
}
