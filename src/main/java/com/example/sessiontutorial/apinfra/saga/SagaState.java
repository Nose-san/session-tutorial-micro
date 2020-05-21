package com.example.sessiontutorial.apinfra.saga;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Component
public class SagaState {
    private String sagaId;

    private SagaTransactionMode mode;

    private int currentStep;

    public SagaState(){
        this.sagaId = UUID.randomUUID().toString();
    }

    public String getSagaId() {
        return sagaId;
    }

    public void updateSagaTransactionMode(String commandReplyOutcome){

    }
}
