package com.example.sessiontutorial.apinfra.saga;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class CreateOrderSaga {

    private List<SagaParticipant> sagaDefinition;

    public  List<SagaParticipant> getSagaDefinition(){
        return sagaDefinition;
    }
}
