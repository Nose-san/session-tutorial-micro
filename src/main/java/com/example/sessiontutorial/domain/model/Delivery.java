package com.example.sessiontutorial.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;

    private Date deliveryDate;

}
