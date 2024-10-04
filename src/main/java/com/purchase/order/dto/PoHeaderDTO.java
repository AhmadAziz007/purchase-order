package com.purchase.order.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PoHeaderDTO {
    private Long pohId;
    private Date datetime;
    private String description;
    private Integer totalPrice;
    private Integer totalCost;
    private String createdBy;
    private String updatedBy;
    private Date createdDatetime;
    private Date updatedDatetime;
}
