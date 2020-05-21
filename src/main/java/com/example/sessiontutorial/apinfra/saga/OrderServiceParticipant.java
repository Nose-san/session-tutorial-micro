package com.example.sessiontutorial.apinfra.saga;

import com.example.sessiontutorial.domain.model.Order;
import com.example.sessiontutorial.domain.model.OrderLine;
import com.example.sessiontutorial.domain.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("orderServiceParticipant")
public class OrderServiceParticipant implements SagaParticipant{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void forwardTransaction(CreateOrderSagaState createOrderSagaState){
        Order order = createOrderSagaState.getOrder();
        orderRepository.createOrder(order);
        List<OrderLine> orderLines = order.getOrderLines();
        orderRepository.createOrderLines(orderLines);
    }

    @Override
    public void compensatingTransaction(CreateOrderSagaState createOrderSagaState){
        orderRepository.deleteOrderLines(createOrderSagaState.getOrder().getId());
        orderRepository.deleteOrder(createOrderSagaState.getOrder().getId());

    }
}
