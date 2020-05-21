package com.example.sessiontutorial.domain.repository.delivery;

import com.example.sessiontutorial.apinfra.exception.BusinessException;
import com.example.sessiontutorial.apinfra.saga.command.common.Command;
import com.example.sessiontutorial.apinfra.saga.command.common.Outcome;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository {
    void send(Command command) throws BusinessException;
}
