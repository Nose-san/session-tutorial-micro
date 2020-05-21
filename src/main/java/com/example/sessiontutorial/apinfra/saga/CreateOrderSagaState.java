package com.example.sessiontutorial.apinfra.saga;

import com.example.sessiontutorial.domain.model.Account;
import com.example.sessiontutorial.domain.model.Order;
import com.example.sessiontutorial.domain.model.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class CreateOrderSagaState extends SagaState{
    private Order order;
    private List<OrderLine> orderLines;
    private Account account;


    public CreateOrderSagaState(Order order, List<OrderLine> orderLines, Account account){
        super();
        this.order = order;
        this.orderLines = orderLines;
        this.account = account;

    }

    public Order getOrder(){
        return order;
    }
}
