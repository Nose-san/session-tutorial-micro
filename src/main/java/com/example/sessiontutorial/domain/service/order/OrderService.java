/*
 * Copyright(c) 2013 NTT DATA Corporation. Copyright(c) 2013 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.sessiontutorial.domain.service.order;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import com.example.sessiontutorial.apinfra.exception.BusinessException;
import com.example.sessiontutorial.apinfra.saga.CreateOrderSagaManager;
import com.example.sessiontutorial.apinfra.saga.CreateOrderSagaState;
import com.example.sessiontutorial.apinfra.saga.SagaParticipant;
import com.example.sessiontutorial.apinfra.saga.SagaState;
import com.example.sessiontutorial.domain.model.*;
import com.example.sessiontutorial.domain.service.account.AccountService;
import com.example.sessiontutorial.domain.service.delivery.DeliveryService;
import com.example.sessiontutorial.domain.service.userdetails.AccountDetails;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.sessiontutorial.domain.repository.order.OrderRepository;
import com.example.sessiontutorial.domain.service.goods.GoodsService;

@Service
public class OrderService {

    @Inject
    Mapper beanMapper;

    @Inject
    CreateOrderSagaManager createOrderSagaManager;

    @Inject
    DeliveryService deliveryService;

    public Order purchase(Account account, Cart cart, String signature) throws BusinessException {
        if (cart.isEmpty()) {
            ResultMessages messages = ResultMessages.error();
            messages.add("e.st.od.8001");
            throw new EmptyCartOrderException(messages);
        }

        if (!Objects.equals(cart.calcSignature(), signature)) {
            ResultMessages messages = ResultMessages.error();
            messages.add("e.st.od.8002");
            throw new InvalidCartOrderException(messages);
        }

        String orderId = UUID.randomUUID().toString();

        Order order = beanMapper.map(cart, Order.class);
        order.setOrderIdToALllOrderLines(orderId);
        order.setEmail(account.getEmail());
        order.setOrderDate(new Date());
        List<OrderLine> orderLines = order.getOrderLines();

        CreateOrderSagaState data = new CreateOrderSagaState(order, orderLines, account);
        //createOrderSagaState.setAccount(account);
        //createOrderSagaState.setOrderLines(orderLines);
        //createOrderSagaState.setOrder(order);

        createOrderSagaManager.create(data);

        /*
        //orderRepository.createOrder(order);
        orderRepository.createOrderLines(orderLines);

        for (OrderLine orderLine : orderLines) {
             goodsService.decreaseStock(orderLine.getGoods(), orderLine.getQuantity());
        }
        accountService.addCreditCharge(account, order.getTotalAmount());

         */
        deliveryService.schedule(order);


        cart.clear();


        return order;
    }

}
